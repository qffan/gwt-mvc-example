/**
 * Copyright 2009 by Google, Inc.
 */
package com.stonedrobin.client.api;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RobinServiceAsync {
    void logIn(String user, String password, AsyncCallback<Boolean> result);

    void logOut(AsyncCallback<Boolean> result);
}
