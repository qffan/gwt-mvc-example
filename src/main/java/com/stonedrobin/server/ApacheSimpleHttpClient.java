/**
 * Copyright 2009 by Google, Inc.
 */
package com.stonedrobin.server;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

/**
 * An implementation of the client based on Apache HTTP client.
 */
public class ApacheSimpleHttpClient implements SimpleHttpClient {
    private final HttpClient client;

    /**
     * Creates a client instance that delegates to the given HTTP client.
     *
     * @param client client
     */
    public ApacheSimpleHttpClient(HttpClient client) {
        this.client = client;
    }

    /**
     * Creates a GET method for a particular URI. Primarily intended for unit tests.
     *
     * @param url URI
     * @return method instance
     */
    HttpMethod createGetMethod(String url) {
        return new GetMethod(url);
    }

    /**
     * Creates a POST method for a particular URI. Primarily intended for unit tests.
     *
     * @param url URI
     * @return method instance
     */
    HttpMethod createPostMethod(String url) {
        return new PostMethod(url);
    }

    public Response doGet(String uri, Header... headers) throws IOException {
        final HttpMethod method = createGetMethod(uri);

        for (Header header : headers) {
            method.addRequestHeader(header);
        }

        final int status = client.executeMethod(method);
        final String body = method.getResponseBodyAsString();

        return new Response() {
            public int getStatus() {
                return status;
            }

            public String getBody() {
                return body;
            }
        };
    }

    public Response doPost(String url, Header... headers) throws IOException {
        final HttpMethod method = createPostMethod(url);

        final int status = client.executeMethod(method);
        final String body = method.getResponseBodyAsString();

        return new Response() {
            public int getStatus() {
                return status;
            }

            public String getBody() {
                return body;
            }
        };
    }
}
