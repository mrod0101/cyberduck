FROM ubuntu:20.04
ENTRYPOINT ["/usr/bin/duck"]
CMD ["--version"]

COPY opt/duck /opt/duck
RUN ln -s /opt/duck/bin/duck /usr/bin/duck
