/**
 * Copyright 2008-2009 by Stoned Robin
 */
package com.stonedrobin.client;

public interface MainConstants extends SystemConstants {

    @DefaultStringValue("Are you sure you are logged in?")
    String getLogoutFailure();

    @DefaultStringValue("Log Out")
    String getLogoutButton();
}
