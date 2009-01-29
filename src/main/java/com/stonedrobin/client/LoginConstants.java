/**
 * Copyright 2009 by Google, Inc.
 */
package com.stonedrobin.client;

public interface LoginConstants extends SystemConstants {

    @DefaultStringValue("Wrong user name/password combination")
    String getLoginFailure();

    @DefaultStringValue("Log In")
    String getLoginButton();

    @DefaultStringValue("User")
    String getUserLabel();

    @DefaultStringValue("Password")
    String getPasswordLabel();
}
