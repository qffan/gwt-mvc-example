/**
 * Copyright 2009 by Google, Inc.
 */
package com.stonedrobin.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.stonedrobin.client.api.RobinService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.params.HttpClientParams;

import java.io.IOException;

public class Robin extends RemoteServiceServlet implements RobinService {


    static final String LOGIN_URL = "http://twitter.com/account/verify_credentials.xml";
    static final String LOGOUT_URL = "http://twitter.com/account/end_session.xml";

    private final SimpleHttpClient client;

    public Robin() {
        this(new ApacheSimpleHttpClient(createHttpClient()));
    }

    public Robin(SimpleHttpClient client) {
        this.client = client;
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

        return response.getStatus() == HttpStatus.SC_OK;
    }

    /**
     * Builds an authorization header corresponding to the given user name/password pair.
     *
     * @param user     user name
     * @param password password
     * @return authorization header value
     */
    String buildAuthorizationHeader(String user, String password) {
        String authentication = user + ":" + password;

        return "Basic " + new String(Base64.encodeBase64(authentication.getBytes()));
    }


    public boolean logOut() {
        SimpleHttpClient.Response response;
        try {
            response = client.doPost(LOGOUT_URL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return response.getStatus() == HttpStatus.SC_OK;
    }
}