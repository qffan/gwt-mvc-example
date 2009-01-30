/**
 * Copyright 2008-2009 by Stoned Robin
 */
package com.stonedrobin.client.api;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("stonedrobin")
public interface RobinService extends RemoteService {
    boolean logIn(String user, String password);

    boolean logOut();
}
