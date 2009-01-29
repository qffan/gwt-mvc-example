/**
 * Copyright 2009 by Google, Inc.
 */
package com.stonedrobin.client;

/**
 * Login model.
 */
public interface LoginModel {
    /**
     * Returns user name.
     *
     * @return user name
     */
    String getUser();

    /**
     * Returns password.
     *
     * @return password
     */
    String getPassword();
}
