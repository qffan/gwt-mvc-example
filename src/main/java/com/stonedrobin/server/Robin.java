/**
 * Copyright 2008-2009 by Stoned Robin
 */
package com.stonedrobin.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.stonedrobin.client.api.RobinService;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;

import javax.servlet.http.Cookie;

public class Robin extends RemoteServiceServlet implements RobinService {

  private static final String LOGIN_URL = "http://twitter.com/account/verify_credentials.xml";
  private static final String LOGOUT_URL = "http://twitter.com/account/end_session.xml";

  static final String COOKIE = "credentials";

  private final SimpleHttpClient client;

  private CookieHolder cookieHolder;

  public Robin() {
    client = new ApacheSimpleHttpClient(createHttpClient());
    cookieHolder = new DefaultCookieHolder();
  }

  Robin(SimpleHttpClient client, CookieHolder cookieHolder) {
    this.client = client;
    this.cookieHolder = cookieHolder;
  }

  private static HttpClient createHttpClient() {
    HttpClient client = new HttpClient();

    HttpClientParams params = new HttpClientParams();
    params.setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
    client.setParams(params);

    return client;
  }

  public boolean logIn(String user, String password) {
    Header header = new Header("Authorization", buildAuthorizationHeader(user, password));

    SimpleHttpClient.Response response;
    try {
      response = client.doGet(LOGIN_URL, header);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    if (response.getStatus() != HttpStatus.SC_OK) {
      return false;
    }

    cookieHolder.setCookie(COOKIE, new CredentialsCodec().encode(user, password));

    return true;
  }

  /**
   * Builds an authorization header corresponding to the given user name/password pair.
   *
   * @param user user name
   * @param password password
   * @return authorization header value
   */
  String buildAuthorizationHeader(String user, String password) {
    String authentication = user + ":" + password;

    return "Basic " + new String(Base64.encodeBase64(authentication.getBytes()));
  }


  public boolean logOut() {
    String cookie = cookieHolder.getCookie(COOKIE);
    if (cookie.length() == 0) {
      return  false;
    }
    
    NameValuePair pair = new CredentialsCodec().decode(cookie);

    String user = pair.getName();
    String password = pair.getValue();

    Header header = new Header("Authorization", buildAuthorizationHeader(user, password));

    SimpleHttpClient.Response response;
    try {
      response = client.doPost(LOGOUT_URL, header);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    if (response.getStatus() != HttpStatus.SC_OK) {
      return false;
    }

    cookieHolder.setCookie(COOKIE, "");

    return true;
  }

  /**
   * Provides access to a cookie.
   */
  interface CookieHolder {

    String getCookie(String name);

    void setCookie(String name, String value);
  }

  public class DefaultCookieHolder implements CookieHolder {
      public String getCookie(String name) {
        for (Cookie cookie : getCookies()) {
          if (name.equals(cookie.getName())) {
            return cookie.getValue();
          }
        }

        return "";
      }

    Cookie[] getCookies() {
      return getThreadLocalRequest().getCookies();
    }

    public void setCookie(String name, String value) {
        getThreadLocalResponse().addCookie(new Cookie(name, value));
      }
  }
}