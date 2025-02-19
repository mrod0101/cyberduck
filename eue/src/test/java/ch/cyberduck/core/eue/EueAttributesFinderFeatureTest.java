package ch.cyberduck.core.eue;

/*
 * Copyright (c) 2002-2021 iterate GmbH. All rights reserved.
 * https://cyberduck.io/
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

import ch.cyberduck.core.AlphanumericRandomStringService;
import ch.cyberduck.core.AttributedList;
import ch.cyberduck.core.DescriptiveUrl;
import ch.cyberduck.core.DisabledConnectionCallback;
import ch.cyberduck.core.DisabledListProgressListener;
import ch.cyberduck.core.DisabledLoginCallback;
import ch.cyberduck.core.DisabledPasswordCallback;
import ch.cyberduck.core.Path;
import ch.cyberduck.core.PathAttributes;
import ch.cyberduck.core.SimplePathPredicate;
import ch.cyberduck.core.eue.io.swagger.client.model.ShareCreationResponseEntry;
import ch.cyberduck.core.features.Delete;
import ch.cyberduck.core.http.HttpResponseOutputStream;
import ch.cyberduck.core.io.Checksum;
import ch.cyberduck.core.io.StreamCopier;
import ch.cyberduck.core.transfer.TransferStatus;
import ch.cyberduck.test.IntegrationTest;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;

import static ch.cyberduck.core.AbstractPath.Type.directory;
import static ch.cyberduck.core.AbstractPath.Type.placeholder;
import static org.junit.Assert.*;

@Category(IntegrationTest.class)
public class EueAttributesFinderFeatureTest extends AbstractEueSessionTest {

    @Test
    public void testRoot() throws Exception {
        final EueResourceIdProvider fileid = new EueResourceIdProvider(session);
        final PathAttributes attr = new EueAttributesFinderFeature(session, fileid).find(new Path("/", EnumSet.of(Path.Type.directory)));
        assertNotEquals(PathAttributes.EMPTY, attr);
        assertNotNull(attr.getETag());
    }

    @Test
    public void testFindIfNoneMatch() throws Exception {
        final EueResourceIdProvider fileid = new EueResourceIdProvider(session);
        final EueWriteFeature feature = new EueWriteFeature(session, fileid);
        final Path container = new EueDirectoryFeature(session, fileid).mkdir(new Path(new AlphanumericRandomStringService().random(), EnumSet.of(Path.Type.directory)), new TransferStatus());
        final Path file = new Path(container, new AlphanumericRandomStringService().random(), EnumSet.of(Path.Type.file));
        final byte[] content = RandomUtils.nextBytes(4128);
        final TransferStatus status = new TransferStatus().withLength(content.length);
        final Checksum checksum = feature.checksum(file, status).compute(new ByteArrayInputStream(content), new TransferStatus().withLength(content.length));
        status.withChecksum(checksum);
        final HttpResponseOutputStream<EueWriteFeature.Chunk> out = feature.write(file, status, new DisabledConnectionCallback());
        final ByteArrayInputStream in = new ByteArrayInputStream(content);
        final TransferStatus progress = new TransferStatus();
        new StreamCopier(new TransferStatus(), progress).transfer(in, out);
        final AttributedList<Path> list = new EueListService(session, fileid).list(file.getParent(), new DisabledListProgressListener());
        assertNotNull(list.find(new SimplePathPredicate(file)));
        final String metaEtag = list.find(new SimplePathPredicate(file)).attributes().getETag();
        assertNotNull(metaEtag);
        final PathAttributes attr = new EueAttributesFinderFeature(session, fileid).find(file);
        assertNotNull(attr.getETag());
        assertEquals(attr.getETag(), metaEtag);
        final PathAttributes ifNoneMatch = new EueAttributesFinderFeature(session, fileid).find(new Path(file).withAttributes(attr));
        assertEquals(attr.getETag(), ifNoneMatch.getETag());
        assertSame(attr, ifNoneMatch);
        assertNotSame(attr, new EueAttributesFinderFeature(session, fileid).find(file));
        new EueDeleteFeature(session, fileid).delete(Collections.singletonList(container), new DisabledLoginCallback(), new Delete.DisabledCallback());
    }

    @Test
    public void testChangeETagPropagatingToRoot() throws Exception {
        final EueResourceIdProvider fileid = new EueResourceIdProvider(session);
        final EueWriteFeature feature = new EueWriteFeature(session, fileid);
        final String rootEtag = new EueAttributesFinderFeature(session, fileid).find(new Path("/", EnumSet.of(Path.Type.directory))).getETag();
        assertNotNull(rootEtag);
        final Path firstlevel = new EueDirectoryFeature(session, fileid).mkdir(new Path(new AlphanumericRandomStringService().random(), EnumSet.of(Path.Type.directory)), new TransferStatus());
        assertNotNull(firstlevel.attributes().getETag());
        assertEquals(firstlevel.attributes().getETag(), new EueAttributesFinderFeature(session, fileid).find(firstlevel).getETag());
        final Path secondlevel = new EueDirectoryFeature(session, fileid).mkdir(new Path(firstlevel, new AlphanumericRandomStringService().random(), EnumSet.of(Path.Type.directory)), new TransferStatus());
        assertNotNull(secondlevel.attributes().getETag());
        assertNotNull(secondlevel.attributes().getETag());
        assertEquals(secondlevel.attributes().getETag(), new EueAttributesFinderFeature(session, fileid).find(secondlevel).getETag());
        final Path secondlevelSibling = new EueDirectoryFeature(session, fileid).mkdir(new Path(firstlevel, new AlphanumericRandomStringService().random(), EnumSet.of(Path.Type.directory)), new TransferStatus());
        final Path file = new EueTouchFeature(session, fileid).touch(new Path(secondlevel, new AlphanumericRandomStringService().random(), EnumSet.of(Path.Type.file)), new TransferStatus());
        assertEquals(secondlevelSibling.attributes().getETag(), new EueAttributesFinderFeature(session, fileid).find(secondlevelSibling).getETag());
        assertNotEquals(secondlevel.attributes().getETag(), new EueAttributesFinderFeature(session, fileid).find(secondlevel).getETag());
        assertNotEquals(firstlevel.attributes().getETag(), new EueAttributesFinderFeature(session, fileid).find(firstlevel).getETag());
        assertNotEquals(rootEtag, new EueAttributesFinderFeature(session, fileid).find(new Path("/", EnumSet.of(Path.Type.directory))).getETag());
        new EueDeleteFeature(session, fileid).delete(Arrays.asList(firstlevel, secondlevel), new DisabledLoginCallback(), new Delete.DisabledCallback());
    }

    @Test
    public void testFindFeatureForSharedFolder() throws Exception {
        final EueResourceIdProvider fileid = new EueResourceIdProvider(session);
        final Path folder = new EueDirectoryFeature(session, fileid).mkdir(new Path(new AlphanumericRandomStringService().random(), EnumSet.of(Path.Type.directory)), new TransferStatus());
        final ShareCreationResponseEntry shareCreationResponseEntry = createShare(fileid, folder);
        final String shareName = shareCreationResponseEntry.getEntity().getName();
        final PathAttributes attr = new EueAttributesFinderFeature(session, fileid).find(folder, new DisabledListProgressListener());
        assertNotNull(attr.getLink());
        assertEquals(attr.getLink(), new EueShareUrlProvider(session.getHost(), session.userShares()).toUrl(folder).find(DescriptiveUrl.Type.signed));
        new EueDeleteFeature(session, fileid).delete(Collections.singletonList(folder), new DisabledPasswordCallback(), new Delete.DisabledCallback());
    }

    @Test
    public void testFindFeatureForSharedFile() throws Exception {
        final EueResourceIdProvider fileid = new EueResourceIdProvider(session);
        final Path sourceFolder = new EueDirectoryFeature(session, fileid).mkdir(new Path(new AlphanumericRandomStringService().random(), EnumSet.of(Path.Type.directory)), new TransferStatus());
        final Path file = new Path(sourceFolder, new AlphanumericRandomStringService().random(), EnumSet.of(Path.Type.file));
        createFile(file, RandomUtils.nextBytes(0));
        assertTrue(new EueFindFeature(session, fileid).find(file));
        final ShareCreationResponseEntry shareCreationResponseEntry = createShare(fileid, file);
        final String shareName = shareCreationResponseEntry.getEntity().getName();
        final PathAttributes attr = new EueAttributesFinderFeature(session, fileid).find(file, new DisabledListProgressListener());
        assertNotNull(attr.getLink());
        assertEquals(attr.getLink(), new EueShareUrlProvider(session.getHost(), session.userShares()).toUrl(file).find(DescriptiveUrl.Type.signed));
        new EueDeleteFeature(session, fileid).delete(Collections.singletonList(sourceFolder), new DisabledPasswordCallback(), new Delete.DisabledCallback());
    }

    @Test
    public void testDeleteFileInTrash() throws Exception {
        final EueResourceIdProvider fileid = new EueResourceIdProvider(session);
        final Path trash = new Path("Gelöschte Dateien", EnumSet.of(directory, placeholder));
        final PathAttributes attr = new EueAttributesFinderFeature(session, fileid).find(trash);
        assertTrue(attr.getPermission().isReadable());
        assertTrue(attr.getPermission().isExecutable());
        assertFalse(attr.getPermission().isWritable());
    }
}
