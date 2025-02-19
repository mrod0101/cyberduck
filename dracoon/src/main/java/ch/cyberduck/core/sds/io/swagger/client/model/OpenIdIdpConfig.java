/*
 * DRACOON API
 * REST Web Services for DRACOON<br><br>This page provides an overview of all available and documented DRACOON APIs, which are grouped by tags.<br>Each tag provides a collection of APIs that are intended for a specific area of the DRACOON.<br><br><a title='Developer Information' href='https://developer.dracoon.com'>Developer Information</a>&emsp;&emsp;<a title='Get SDKs on GitHub' href='https://github.com/dracoon'>Get SDKs on GitHub</a><br><br><a title='Terms of service' href='https://www.dracoon.com/terms/general-terms-and-conditions/'>Terms of service</a>
 *
 * OpenAPI spec version: 4.30.0-beta.4
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package ch.cyberduck.core.sds.io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
/**
 * OpenID Connect IDP configuration
 */
@Schema(description = "OpenID Connect IDP configuration")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-08-16T11:28:10.116221+02:00[Europe/Zurich]")
public class OpenIdIdpConfig {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("issuer")
  private String issuer = null;

  @JsonProperty("authorizationEndPointUrl")
  private String authorizationEndPointUrl = null;

  @JsonProperty("tokenEndPointUrl")
  private String tokenEndPointUrl = null;

  @JsonProperty("userInfoEndPointUrl")
  private String userInfoEndPointUrl = null;

  @JsonProperty("jwksEndPointUrl")
  private String jwksEndPointUrl = null;

  @JsonProperty("clientId")
  private String clientId = null;

  @JsonProperty("clientSecret")
  private String clientSecret = null;

  /**
   * &amp;#128640; Since v4.11.0  Flow, which is used at authentication
   */
  public enum FlowEnum {
    AUTHORIZATION_CODE("authorization_code"),
    HYBRID("hybrid");

    private String value;

    FlowEnum(String value) {
      this.value = value;
    }
    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    @JsonCreator
    public static FlowEnum fromValue(String text) {
      for (FlowEnum b : FlowEnum.values()) {
        if (b.value.equals(text)) {
          return b;
        }
      }
      return null;
    }

  }  @JsonProperty("flow")
  private FlowEnum flow = null;

  @JsonProperty("scopes")
  private List<String> scopes = null;

  @JsonProperty("redirectUris")
  private List<String> redirectUris = null;

  @JsonProperty("pkceEnabled")
  private Boolean pkceEnabled = false;

  @JsonProperty("pkceChallengeMethod")
  private String pkceChallengeMethod = null;

  @JsonProperty("mappingClaim")
  private String mappingClaim = null;

  @JsonProperty("fallbackMappingClaim")
  private String fallbackMappingClaim = null;

  /**
   * &amp;#128640; Since v4.23.0  Source, which is used to get user information at the import or update of a user.
   */
  public enum UserInfoSourceEnum {
    USER_INFO_ENDPOINT("user_info_endpoint"),
    ID_TOKEN("id_token");

    private String value;

    UserInfoSourceEnum(String value) {
      this.value = value;
    }
    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    @JsonCreator
    public static UserInfoSourceEnum fromValue(String text) {
      for (UserInfoSourceEnum b : UserInfoSourceEnum.values()) {
        if (b.value.equals(text)) {
          return b;
        }
      }
      return null;
    }

  }  @JsonProperty("userInfoSource")
  private UserInfoSourceEnum userInfoSource = null;

  @JsonProperty("userImportEnabled")
  private Boolean userImportEnabled = false;

  @JsonProperty("userImportGroup")
  private Long userImportGroup = null;

  @JsonProperty("userUpdateEnabled")
  private Boolean userUpdateEnabled = false;

  @JsonProperty("userManagementUrl")
  private String userManagementUrl = null;

  public OpenIdIdpConfig id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * ID
   * @return id
  **/
  @Schema(required = true, description = "ID")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public OpenIdIdpConfig name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Name of the IDP
   * @return name
  **/
  @Schema(description = "Name of the IDP")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public OpenIdIdpConfig issuer(String issuer) {
    this.issuer = issuer;
    return this;
  }

   /**
   * Issuer identifier of the IDP  The value is a case sensitive URL.
   * @return issuer
  **/
  @Schema(description = "Issuer identifier of the IDP  The value is a case sensitive URL.")
  public String getIssuer() {
    return issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }

  public OpenIdIdpConfig authorizationEndPointUrl(String authorizationEndPointUrl) {
    this.authorizationEndPointUrl = authorizationEndPointUrl;
    return this;
  }

   /**
   * URL of the authorization endpoint
   * @return authorizationEndPointUrl
  **/
  @Schema(description = "URL of the authorization endpoint")
  public String getAuthorizationEndPointUrl() {
    return authorizationEndPointUrl;
  }

  public void setAuthorizationEndPointUrl(String authorizationEndPointUrl) {
    this.authorizationEndPointUrl = authorizationEndPointUrl;
  }

  public OpenIdIdpConfig tokenEndPointUrl(String tokenEndPointUrl) {
    this.tokenEndPointUrl = tokenEndPointUrl;
    return this;
  }

   /**
   * URL of the token endpoint
   * @return tokenEndPointUrl
  **/
  @Schema(description = "URL of the token endpoint")
  public String getTokenEndPointUrl() {
    return tokenEndPointUrl;
  }

  public void setTokenEndPointUrl(String tokenEndPointUrl) {
    this.tokenEndPointUrl = tokenEndPointUrl;
  }

  public OpenIdIdpConfig userInfoEndPointUrl(String userInfoEndPointUrl) {
    this.userInfoEndPointUrl = userInfoEndPointUrl;
    return this;
  }

   /**
   * URL of the user info endpoint
   * @return userInfoEndPointUrl
  **/
  @Schema(description = "URL of the user info endpoint")
  public String getUserInfoEndPointUrl() {
    return userInfoEndPointUrl;
  }

  public void setUserInfoEndPointUrl(String userInfoEndPointUrl) {
    this.userInfoEndPointUrl = userInfoEndPointUrl;
  }

  public OpenIdIdpConfig jwksEndPointUrl(String jwksEndPointUrl) {
    this.jwksEndPointUrl = jwksEndPointUrl;
    return this;
  }

   /**
   * URL of the JWKS endpoint
   * @return jwksEndPointUrl
  **/
  @Schema(description = "URL of the JWKS endpoint")
  public String getJwksEndPointUrl() {
    return jwksEndPointUrl;
  }

  public void setJwksEndPointUrl(String jwksEndPointUrl) {
    this.jwksEndPointUrl = jwksEndPointUrl;
  }

  public OpenIdIdpConfig clientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

   /**
   * ID of the OpenID client
   * @return clientId
  **/
  @Schema(description = "ID of the OpenID client")
  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public OpenIdIdpConfig clientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
    return this;
  }

   /**
   * Secret, which client uses at authentication.
   * @return clientSecret
  **/
  @Schema(description = "Secret, which client uses at authentication.")
  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public OpenIdIdpConfig flow(FlowEnum flow) {
    this.flow = flow;
    return this;
  }

   /**
   * &amp;#128640; Since v4.11.0  Flow, which is used at authentication
   * @return flow
  **/
  @Schema(description = "&#128640; Since v4.11.0  Flow, which is used at authentication")
  public FlowEnum getFlow() {
    return flow;
  }

  public void setFlow(FlowEnum flow) {
    this.flow = flow;
  }

  public OpenIdIdpConfig scopes(List<String> scopes) {
    this.scopes = scopes;
    return this;
  }

  public OpenIdIdpConfig addScopesItem(String scopesItem) {
    if (this.scopes == null) {
      this.scopes = new ArrayList<>();
    }
    this.scopes.add(scopesItem);
    return this;
  }

   /**
   * List of requested scopes  Usually &#x60;openid&#x60; and the names of the requested claims.
   * @return scopes
  **/
  @Schema(description = "List of requested scopes  Usually `openid` and the names of the requested claims.")
  public List<String> getScopes() {
    return scopes;
  }

  public void setScopes(List<String> scopes) {
    this.scopes = scopes;
  }

  public OpenIdIdpConfig redirectUris(List<String> redirectUris) {
    this.redirectUris = redirectUris;
    return this;
  }

  public OpenIdIdpConfig addRedirectUrisItem(String redirectUrisItem) {
    if (this.redirectUris == null) {
      this.redirectUris = new ArrayList<>();
    }
    this.redirectUris.add(redirectUrisItem);
    return this;
  }

   /**
   * URIs, to which a user is redirected after authorization.
   * @return redirectUris
  **/
  @Schema(description = "URIs, to which a user is redirected after authorization.")
  public List<String> getRedirectUris() {
    return redirectUris;
  }

  public void setRedirectUris(List<String> redirectUris) {
    this.redirectUris = redirectUris;
  }

  public OpenIdIdpConfig pkceEnabled(Boolean pkceEnabled) {
    this.pkceEnabled = pkceEnabled;
    return this;
  }

   /**
   * Determines whether PKCE is enabled.  cf. [RFC 7636](https://tools.ietf.org/html/rfc7636)
   * @return pkceEnabled
  **/
  @Schema(description = "Determines whether PKCE is enabled.  cf. [RFC 7636](https://tools.ietf.org/html/rfc7636)")
  public Boolean isPkceEnabled() {
    return pkceEnabled;
  }

  public void setPkceEnabled(Boolean pkceEnabled) {
    this.pkceEnabled = pkceEnabled;
  }

  public OpenIdIdpConfig pkceChallengeMethod(String pkceChallengeMethod) {
    this.pkceChallengeMethod = pkceChallengeMethod;
    return this;
  }

   /**
   * PKCE code challenge method.  cf. [RFC 7636](https://tools.ietf.org/html/rfc7636)
   * @return pkceChallengeMethod
  **/
  @Schema(description = "PKCE code challenge method.  cf. [RFC 7636](https://tools.ietf.org/html/rfc7636)")
  public String getPkceChallengeMethod() {
    return pkceChallengeMethod;
  }

  public void setPkceChallengeMethod(String pkceChallengeMethod) {
    this.pkceChallengeMethod = pkceChallengeMethod;
  }

  public OpenIdIdpConfig mappingClaim(String mappingClaim) {
    this.mappingClaim = mappingClaim;
    return this;
  }

   /**
   * Name of the claim which is used for the user mapping.
   * @return mappingClaim
  **/
  @Schema(description = "Name of the claim which is used for the user mapping.")
  public String getMappingClaim() {
    return mappingClaim;
  }

  public void setMappingClaim(String mappingClaim) {
    this.mappingClaim = mappingClaim;
  }

  public OpenIdIdpConfig fallbackMappingClaim(String fallbackMappingClaim) {
    this.fallbackMappingClaim = fallbackMappingClaim;
    return this;
  }

   /**
   * Name of the claim which is used for the user mapping fallback.
   * @return fallbackMappingClaim
  **/
  @Schema(description = "Name of the claim which is used for the user mapping fallback.")
  public String getFallbackMappingClaim() {
    return fallbackMappingClaim;
  }

  public void setFallbackMappingClaim(String fallbackMappingClaim) {
    this.fallbackMappingClaim = fallbackMappingClaim;
  }

  public OpenIdIdpConfig userInfoSource(UserInfoSourceEnum userInfoSource) {
    this.userInfoSource = userInfoSource;
    return this;
  }

   /**
   * &amp;#128640; Since v4.23.0  Source, which is used to get user information at the import or update of a user.
   * @return userInfoSource
  **/
  @Schema(description = "&#128640; Since v4.23.0  Source, which is used to get user information at the import or update of a user.")
  public UserInfoSourceEnum getUserInfoSource() {
    return userInfoSource;
  }

  public void setUserInfoSource(UserInfoSourceEnum userInfoSource) {
    this.userInfoSource = userInfoSource;
  }

  public OpenIdIdpConfig userImportEnabled(Boolean userImportEnabled) {
    this.userImportEnabled = userImportEnabled;
    return this;
  }

   /**
   * Determines if a DRACOON account is automatically created for a new user  who successfully logs on with his / her AD / IDP account.
   * @return userImportEnabled
  **/
  @Schema(description = "Determines if a DRACOON account is automatically created for a new user  who successfully logs on with his / her AD / IDP account.")
  public Boolean isUserImportEnabled() {
    return userImportEnabled;
  }

  public void setUserImportEnabled(Boolean userImportEnabled) {
    this.userImportEnabled = userImportEnabled;
  }

  public OpenIdIdpConfig userImportGroup(Long userImportGroup) {
    this.userImportGroup = userImportGroup;
    return this;
  }

   /**
   * User group that is assigned to users who are created by automatic import.  Reset with &#x60;0&#x60;
   * @return userImportGroup
  **/
  @Schema(description = "User group that is assigned to users who are created by automatic import.  Reset with `0`")
  public Long getUserImportGroup() {
    return userImportGroup;
  }

  public void setUserImportGroup(Long userImportGroup) {
    this.userImportGroup = userImportGroup;
  }

  public OpenIdIdpConfig userUpdateEnabled(Boolean userUpdateEnabled) {
    this.userUpdateEnabled = userUpdateEnabled;
    return this;
  }

   /**
   * Determines if the DRACOON account is updated with data from AD / IDP.  For OpenID Connect, the scopes &#x60;email&#x60; and &#x60;profile&#x60; are needed.
   * @return userUpdateEnabled
  **/
  @Schema(description = "Determines if the DRACOON account is updated with data from AD / IDP.  For OpenID Connect, the scopes `email` and `profile` are needed.")
  public Boolean isUserUpdateEnabled() {
    return userUpdateEnabled;
  }

  public void setUserUpdateEnabled(Boolean userUpdateEnabled) {
    this.userUpdateEnabled = userUpdateEnabled;
  }

  public OpenIdIdpConfig userManagementUrl(String userManagementUrl) {
    this.userManagementUrl = userManagementUrl;
    return this;
  }

   /**
   * URL of the user management UI.  Use empty string to remove.
   * @return userManagementUrl
  **/
  @Schema(description = "URL of the user management UI.  Use empty string to remove.")
  public String getUserManagementUrl() {
    return userManagementUrl;
  }

  public void setUserManagementUrl(String userManagementUrl) {
    this.userManagementUrl = userManagementUrl;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OpenIdIdpConfig openIdIdpConfig = (OpenIdIdpConfig) o;
    return Objects.equals(this.id, openIdIdpConfig.id) &&
        Objects.equals(this.name, openIdIdpConfig.name) &&
        Objects.equals(this.issuer, openIdIdpConfig.issuer) &&
        Objects.equals(this.authorizationEndPointUrl, openIdIdpConfig.authorizationEndPointUrl) &&
        Objects.equals(this.tokenEndPointUrl, openIdIdpConfig.tokenEndPointUrl) &&
        Objects.equals(this.userInfoEndPointUrl, openIdIdpConfig.userInfoEndPointUrl) &&
        Objects.equals(this.jwksEndPointUrl, openIdIdpConfig.jwksEndPointUrl) &&
        Objects.equals(this.clientId, openIdIdpConfig.clientId) &&
        Objects.equals(this.clientSecret, openIdIdpConfig.clientSecret) &&
        Objects.equals(this.flow, openIdIdpConfig.flow) &&
        Objects.equals(this.scopes, openIdIdpConfig.scopes) &&
        Objects.equals(this.redirectUris, openIdIdpConfig.redirectUris) &&
        Objects.equals(this.pkceEnabled, openIdIdpConfig.pkceEnabled) &&
        Objects.equals(this.pkceChallengeMethod, openIdIdpConfig.pkceChallengeMethod) &&
        Objects.equals(this.mappingClaim, openIdIdpConfig.mappingClaim) &&
        Objects.equals(this.fallbackMappingClaim, openIdIdpConfig.fallbackMappingClaim) &&
        Objects.equals(this.userInfoSource, openIdIdpConfig.userInfoSource) &&
        Objects.equals(this.userImportEnabled, openIdIdpConfig.userImportEnabled) &&
        Objects.equals(this.userImportGroup, openIdIdpConfig.userImportGroup) &&
        Objects.equals(this.userUpdateEnabled, openIdIdpConfig.userUpdateEnabled) &&
        Objects.equals(this.userManagementUrl, openIdIdpConfig.userManagementUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, issuer, authorizationEndPointUrl, tokenEndPointUrl, userInfoEndPointUrl, jwksEndPointUrl, clientId, clientSecret, flow, scopes, redirectUris, pkceEnabled, pkceChallengeMethod, mappingClaim, fallbackMappingClaim, userInfoSource, userImportEnabled, userImportGroup, userUpdateEnabled, userManagementUrl);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OpenIdIdpConfig {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    issuer: ").append(toIndentedString(issuer)).append("\n");
    sb.append("    authorizationEndPointUrl: ").append(toIndentedString(authorizationEndPointUrl)).append("\n");
    sb.append("    tokenEndPointUrl: ").append(toIndentedString(tokenEndPointUrl)).append("\n");
    sb.append("    userInfoEndPointUrl: ").append(toIndentedString(userInfoEndPointUrl)).append("\n");
    sb.append("    jwksEndPointUrl: ").append(toIndentedString(jwksEndPointUrl)).append("\n");
    sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
    sb.append("    clientSecret: ").append(toIndentedString(clientSecret)).append("\n");
    sb.append("    flow: ").append(toIndentedString(flow)).append("\n");
    sb.append("    scopes: ").append(toIndentedString(scopes)).append("\n");
    sb.append("    redirectUris: ").append(toIndentedString(redirectUris)).append("\n");
    sb.append("    pkceEnabled: ").append(toIndentedString(pkceEnabled)).append("\n");
    sb.append("    pkceChallengeMethod: ").append(toIndentedString(pkceChallengeMethod)).append("\n");
    sb.append("    mappingClaim: ").append(toIndentedString(mappingClaim)).append("\n");
    sb.append("    fallbackMappingClaim: ").append(toIndentedString(fallbackMappingClaim)).append("\n");
    sb.append("    userInfoSource: ").append(toIndentedString(userInfoSource)).append("\n");
    sb.append("    userImportEnabled: ").append(toIndentedString(userImportEnabled)).append("\n");
    sb.append("    userImportGroup: ").append(toIndentedString(userImportGroup)).append("\n");
    sb.append("    userUpdateEnabled: ").append(toIndentedString(userUpdateEnabled)).append("\n");
    sb.append("    userManagementUrl: ").append(toIndentedString(userManagementUrl)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
