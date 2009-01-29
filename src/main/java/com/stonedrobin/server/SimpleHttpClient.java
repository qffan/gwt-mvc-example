/**
 * Copyright 2009 by Google, Inc.
 */
package com.stonedrobin.server;

import org.apache.commons.httpclient.Header;

import java.io.IOException;

/**
 * A very simple HTTP client dealing primarily with string responses.
 */
public interface SimpleHttpClient {
    /**
     * Executes an HTTP GET request.
     *
     * @param uri     request URI
     * @param headers HTTP headers @return response
     * @return method response
     * @throws java.io.IOException if an IO error occurs
     */
    Response doGet(String uri, Header... headers) throws IOException;

    /**
     * Client response.
     */
    interface Response {
        /**
         * Returns HTTP status.
         *
         * @return status code
         */
        int getStatus();

        /**
         * Returns response body as a string.
         *
         * @return response as a string
         */
        String getBody();
    }

    Response doPost(String url, Header... header) throws IOException;
}
