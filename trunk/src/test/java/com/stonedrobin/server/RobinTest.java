/**
 * Copyright 2009 by Google, Inc.
 */
package com.stonedrobin.server;

import junit.framework.TestCase;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpStatus;
import static org.easymock.EasyMock.*;

import java.io.IOException;

public class RobinTest extends TestCase {

    public void testLogIn_Success() throws IOException {
        verifyLogIn(true, HttpStatus.SC_OK);
    }

    public void testLogIn_Failure() throws IOException {
        verifyLogIn(false, HttpStatus.SC_UNAUTHORIZED);
    }

    private void verifyLogIn(boolean expectedResult, int status) throws IOException {
        SimpleHttpClient client = createMock(SimpleHttpClient.class);

        Robin robin = new Robin(client);

        String user = "Alladin";
        String password = "open sesame";
        String authorization = robin.buildAuthorizationHeader(user, password);

        MockResponse response = new MockResponse();
        response.setStatus(status);

        expect(client.doGet("http://twitter.com/account/verify_credentials.xml", new Header("Authorization", authorization))).andReturn(response);
        replay(client);

        assertEquals(expectedResult, robin.logIn(user, password));

        verify(client);
    }

    public void testBuildAuthorizationHeader() {
        Robin robin = new Robin(null);

        assertEquals("Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==", robin.buildAuthorizationHeader("Aladdin", "open sesame"));
    }

    public void testLogOutUser() throws Exception {
        SimpleHttpClient client = createMock(SimpleHttpClient.class);
        MockResponse response = new MockResponse();
        response.setStatus(200);

        Robin robin = new Robin(client);
//        String user = "Alladin";
//        String password = "open sesame";
//        String authorization = robin.buildAuthorizationHeader(user, password);
//		
//        expect(client.doPost("http://twitter.com/account/end_session.xml",
//				new Header("Authorization", authorization))).andReturn(response);
        expect(client.doPost("http://twitter.com/account/end_session.xml")).andReturn(response);

        replay(client);

        assertTrue(robin.logOut());
        verify(client);
    }

    public void testLogOutUserFailed() throws Exception {
        SimpleHttpClient client = createMock(SimpleHttpClient.class);
        MockResponse response = new MockResponse();
        response.setStatus(401);
        expect(client.doPost("http://twitter.com/account/end_session.xml")).andReturn(response);
        replay(client);
        Robin robin = new Robin(client);
        assertFalse(robin.logOut());
        verify(client);
    }

}
