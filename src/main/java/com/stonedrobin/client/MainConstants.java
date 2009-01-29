/**
 * Copyright 2009 by Google, Inc.
 */
package com.stonedrobin.client;

public interface MainConstants extends SystemConstants {

    @DefaultStringValue("Are you sure you are logged in?")
    String getLogoutFailure();

    @DefaultStringValue("Log Out")
    String getLogoutButton();
}
