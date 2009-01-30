/**
 * Copyright 2008-2009 by Stoned Robin
 */
package com.stonedrobin.server;

import junit.framework.TestCase;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import static org.easymock.EasyMock.expect;
import org.easymock.classextension.EasyMock;
import static org.easymock.classextension.EasyMock.*;
import org.easymock.classextension.IMocksControl;

import java.io.IOException;

public class ApacheSimpleHttpClientTest extends TestCase {
    public void testGetMethod_HeadersAdded() throws IOException {
        HttpClient apacheHttpClient = createMock(HttpClient.class);
        SimpleHttpClient client = new ApacheSimpleHttpClient(apacheHttpClient);

        Header header = new Header("header", "value");
        Header header2 = new Header("another header", "another value");
        expect(apacheHttpClient.executeMethod(HttpMethodMatcher.getWithHeaders(header, header2))).andReturn(0);
        replay(apacheHttpClient);

        client.doGet(null, header, header2);

        verify(apacheHttpClient);
    }

    public void testGetMethod_RequestURISet() throws IOException {
        HttpClient apacheHttpClient = createMock(HttpClient.class);
        SimpleHttpClient client = new ApacheSimpleHttpClient(apacheHttpClient);

        String uri = "http://localhost/"; // must end with trailing slash
        expect(apacheHttpClient.executeMethod(HttpMethodMatcher.getWithURI(uri))).andReturn(0);
        replay(apacheHttpClient);

        client.doGet(uri);

        verify(apacheHttpClient);
    }

    public void testGetStatusCode() throws IOException {
        HttpClient apacheHttpClient = createMock(HttpClient.class);
        SimpleHttpClient client = new ApacheSimpleHttpClient(apacheHttpClient);

        int status = 201;

        expect(apacheHttpClient.executeMethod(EasyMock.<HttpMethod>anyObject())).andReturn(status);
        replay(apacheHttpClient);

        SimpleHttpClient.Response response = client.doGet(null);
        assertEquals(status, response.getStatus());

        verify(apacheHttpClient);
    }

    public void testGetBody() throws IOException {
        IMocksControl control = createControl();

        HttpClient apacheHttpClient = control.createMock(HttpClient.class);
        final GetMethod method = control.createMock(GetMethod.class);

        SimpleHttpClient client = new ApacheSimpleHttpClient(apacheHttpClient) {
            @Override
            HttpMethod createGetMethod(String url) {
                return method;
            }
        };

        String body = "OK";

        expect(apacheHttpClient.executeMethod(EasyMock.<HttpMethod>anyObject())).andReturn(0);
        expect(method.getResponseBodyAsString()).andReturn(body);
        replay(apacheHttpClient);

        SimpleHttpClient.Response response = client.doGet(null);
        assertEquals(body, response.getBody());

        verify(apacheHttpClient);
    }

  public void testPostMethod_HeadersAdded() throws IOException {
      HttpClient apacheHttpClient = createMock(HttpClient.class);
      SimpleHttpClient client = new ApacheSimpleHttpClient(apacheHttpClient);

      Header header = new Header("header", "value");
      Header header2 = new Header("another header", "another value");
    
    GetMethod method = HttpMethodMatcher.postWithHeaders(header, header2);
    expect(apacheHttpClient.executeMethod(method)).andReturn(0);

      replay(apacheHttpClient);

      client.doPost(null, header, header2);

      verify(apacheHttpClient);
  }
    
    public void testPostMethod_RequestURISet() throws IOException {
        HttpClient apacheHttpClient = createMock(HttpClient.class);
        SimpleHttpClient client = new ApacheSimpleHttpClient(apacheHttpClient);

        String uri = "http://localhost/"; // must end with trailing slash
        expect(apacheHttpClient.executeMethod(HttpMethodMatcher.postWithURI(uri))).andReturn(0);
        replay(apacheHttpClient);

        client.doPost(uri);

        verify(apacheHttpClient);
    }

    public void testPostStatusCode() throws IOException {
        HttpClient apacheHttpClient = createMock(HttpClient.class);
        SimpleHttpClient client = new ApacheSimpleHttpClient(apacheHttpClient);

        int status = 201;

        expect(apacheHttpClient.executeMethod(EasyMock.<HttpMethod>anyObject())).andReturn(status);
        replay(apacheHttpClient);

        SimpleHttpClient.Response response = client.doPost(null);
        assertEquals(status, response.getStatus());

        verify(apacheHttpClient);
    }

    public void testPostBody() throws IOException {
        IMocksControl control = createControl();

        HttpClient apacheHttpClient = control.createMock(HttpClient.class);
        final PostMethod method = control.createMock(PostMethod.class);

        SimpleHttpClient client = new ApacheSimpleHttpClient(apacheHttpClient) {
            @Override
            HttpMethod createPostMethod(String url) {
                return method;
            }
        };

        String body = "OK";

        expect(apacheHttpClient.executeMethod(EasyMock.<HttpMethod>anyObject())).andReturn(0);
        expect(method.getResponseBodyAsString()).andReturn(body);
        replay(apacheHttpClient);

        SimpleHttpClient.Response response = client.doPost(null);
        assertEquals(body, response.getBody());

        verify(apacheHttpClient);
    }

}
