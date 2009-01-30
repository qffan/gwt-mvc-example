/**
 * Copyright 2008-2009 by Stoned Robin
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
