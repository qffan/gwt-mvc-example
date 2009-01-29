/**
 * Copyright 2009 by Google, Inc.
 */
package com.stonedrobin.server;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.easymock.IArgumentMatcher;
import org.easymock.classextension.EasyMock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * An EasyMock argument matcher for verifying {@link GetMethod} arguments. For usage instructions see
 * <a href="http://www.easymock.org/">EasyMock</a> documentation.
 */
public class HttpMethodMatcher implements IArgumentMatcher {
    /**
     * A special do-not-care marker used to ignore arguments.
     */
    private static final Object DO_NOT_CARE = new Object();

    private final Class<? extends HttpMethod> expectedType;
    private final Object expectedURI;
    private final List<Header> expectedHeaders;

    /**
     * Matches a GET method with the specific URI.
     *
     * @param uri expected URI
     * @return null
     */
    public static GetMethod getWithURI(String uri) {
        EasyMock.reportMatcher(new HttpMethodMatcher(GetMethod.class, uri, Collections.<Header>emptyList()));
        return null;
    }

    /**
     * Matches a POST method with the specific URI.
     *
     * @param uri expected URI
     * @return null
     */
    public static PostMethod postWithURI(String uri) {
        EasyMock.reportMatcher(new HttpMethodMatcher(PostMethod.class, uri, Collections.<Header>emptyList()));
        return null;
    }

    /**
     * Matches a method with any URI but given request headers.
     *
     * @param headers request header
     * @return null
     */
    public static GetMethod getWithHeaders(Header... headers) {
        EasyMock.reportMatcher(new HttpMethodMatcher(GetMethod.class, DO_NOT_CARE, Arrays.asList(headers)));
        return null;
    }

    private HttpMethodMatcher(Class<? extends HttpMethod> expectedType, Object expectedURI, List<Header> expectedHeaders) {
        this.expectedType = expectedType;
        this.expectedURI = expectedURI;
        this.expectedHeaders = new ArrayList<Header>(expectedHeaders);
    }

    public boolean matches(Object object) {
        if (!(object instanceof HttpMethod)) {
            return false;
        }

        HttpMethod actual = (HttpMethod) object;

        if (expectedType != actual.getClass()) {
            return false;
        }

        if (expectedURI != DO_NOT_CARE) {
            try {
                if (!expectedURI.equals(actual.getURI().toString())) {
                    return false;
                }
            } catch (URIException e) {
                throw new RuntimeException(e);
            }
        }

        List<Header> actualHeaders = Arrays.asList(actual.getRequestHeaders());
        return expectedHeaders.equals(actualHeaders);
    }

    public void appendTo(StringBuffer stringBuffer) {
        stringBuffer.append("Expected ");
        stringBuffer.append(expectedType.getName());
        stringBuffer.append(" method");

        if (expectedURI != DO_NOT_CARE) {
            stringBuffer.append(" with URI: ");
            stringBuffer.append(expectedURI);
        }

        if (!expectedHeaders.isEmpty()) {
            stringBuffer.append(" with headers: ");
            stringBuffer.append(expectedHeaders);
        }
    }
}
