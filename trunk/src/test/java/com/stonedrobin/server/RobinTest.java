/**
 * Copyright 2008-2009 by Stoned Robin
 */
package com.stonedrobin.server;

import junit.framework.TestCase;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpStatus;
import static org.easymock.EasyMock.createStrictControl;
import static org.easymock.EasyMock.expect;
import org.easymock.IMocksControl;
import org.omg.PortableServer.ServantLocatorPackage.CookieHolder;

import java.io.IOException;

import javax.servlet.http.Cookie;

public class RobinTest extends TestCase {
  private static final String LOGIN_URL = "http://twitter.com/account/verify_credentials.xml";
  private static final String LOGOUT_URL = "http://twitter.com/account/end_session.xml";

  private static final String COOKIE = "credentials";

  public void testLogIn_Success() throws IOException {
    IMocksControl control = createStrictControl();

    SimpleHttpClient client = control.createMock(SimpleHttpClient.class);
    Robin.CookieHolder holder = control.createMock(Robin.CookieHolder.class);
    Robin robin = new Robin(client, holder);

    MockResponse response = new MockResponse();
    response.setStatus(HttpStatus.SC_OK);

    String user = "Alladin";
    String password = "open sesame";
    String authorization = robin.buildAuthorizationHeader(user, password);
    Header header = new Header("Authorization", authorization);

    // Record the behavior
    expect(client.doGet(LOGIN_URL, header)).andReturn(response);
    holder.setCookie(COOKIE, new CredentialsCodec().encode(user, password));

    control.replay();

    assertTrue(robin.logIn(user, password));

    control.verify();
  }

  public void testLogIn_Failure() throws IOException {
    IMocksControl control = createStrictControl();

    SimpleHttpClient client = control.createMock(SimpleHttpClient.class);
    Robin.CookieHolder holder = control.createMock(Robin.CookieHolder.class);
    Robin robin = new Robin(client, holder);

    MockResponse response = new MockResponse();
    response.setStatus(HttpStatus.SC_UNAUTHORIZED);

    String user = "Alladin";
    String password = "open sesame";
    String authorization = robin.buildAuthorizationHeader(user, password);
    Header header = new Header("Authorization", authorization);

    // Record the behavior
    expect(client.doGet(LOGIN_URL, header)).andReturn(response);

    control.replay();

    assertFalse(robin.logIn(user, password));

    control.verify();
  }

  public void testBuildAuthorizationHeader() {
    Robin robin = new Robin(null, null);

    assertEquals("Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==",
        robin.buildAuthorizationHeader("Aladdin", "open sesame"));
  }

  public void testLogOut_Success() throws Exception {
    IMocksControl control = createStrictControl();

    SimpleHttpClient client = control.createMock(SimpleHttpClient.class);
    Robin.CookieHolder holder = control.createMock(Robin.CookieHolder.class);
    Robin robin = new Robin(client, holder);

    MockResponse response = new MockResponse();
    response.setStatus(HttpStatus.SC_OK);

    String user = "Alladin";
    String password = "open sesame";
    String authorization = robin.buildAuthorizationHeader(user, password);
    Header header = new Header("Authorization", authorization);

    // Record the behavior
    expect(holder.getCookie(COOKIE)).andReturn(new CredentialsCodec().encode(user, password));
    expect(client.doPost(LOGOUT_URL, header)).andReturn(response);
    holder.setCookie(COOKIE, "");

    control.replay();

    assertTrue(robin.logOut());

    control.verify();
  }

  public void testLogOut_MissingCookie() throws Exception {
    IMocksControl control = createStrictControl();

    SimpleHttpClient client = control.createMock(SimpleHttpClient.class);
    Robin.CookieHolder holder = control.createMock(Robin.CookieHolder.class);
    Robin robin = new Robin(client, holder);

    MockResponse response = new MockResponse();
    response.setStatus(HttpStatus.SC_OK);

    String user = "Alladin";
    String password = "open sesame";
    String authorization = robin.buildAuthorizationHeader(user, password);
    Header header = new Header("Authorization", authorization);

    // Record the behavior
    expect(holder.getCookie(COOKIE)).andReturn("");

    control.replay();

    assertFalse(robin.logOut());

    control.verify();
  }

  public void testLogOut_Failure() throws Exception {
    IMocksControl control = createStrictControl();

    SimpleHttpClient client = control.createMock(SimpleHttpClient.class);
    Robin.CookieHolder holder = control.createMock(Robin.CookieHolder.class);
    Robin robin = new Robin(client, holder);

    MockResponse response = new MockResponse();
    response.setStatus(HttpStatus.SC_UNAUTHORIZED);

    String user = "Alladin";
    String password = "open sesame";
    String authorization = robin.buildAuthorizationHeader(user, password);
    Header header = new Header("Authorization", authorization);

    // Record the behavior
    expect(holder.getCookie(COOKIE)).andReturn(new CredentialsCodec().encode(user, password));
    expect(client.doPost(LOGOUT_URL, header)).andReturn(response);

    control.replay();

    assertFalse(robin.logOut());

    control.verify();
  }

  public void testDefaultCookieHolder() {
    Robin robin = new Robin(null, null);

    Cookie cookie = new Cookie("name", "value");
    Cookie cookie2 = new Cookie("name2", "value2");

    final Cookie[] cookies = new Cookie[]{cookie, cookie2};

    Robin.CookieHolder holder = robin.new DefaultCookieHolder() {
      Cookie[] getCookies() {
        return cookies;
      }
    };

    assertEquals(cookie.getValue(), holder.getCookie(cookie.getName()));
    assertEquals("", holder.getCookie("unknown"));
  }

}
