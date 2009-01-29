/**
 * Copyright 2009 by Google, Inc.
 */
package com.stonedrobin.server;

/**
 * Mock implementation of HTTP client response.
 */
public class MockResponse implements SimpleHttpClient.Response {
    private int status;
    private String body = "";

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
