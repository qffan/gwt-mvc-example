/**
 * Copyright 2008-2009 by Stoned Robin
 */
package com.stonedrobin.client;

/**
 * Application services.
 */
public interface Application {
    /**
     * Shows a screen with the given ID.
     *
     * @param screen screen ID
     */
    void show(Screen screen);

    /**
     * Displays an error message.
     *
     * @param message error text
     */
    void displayError(String message);
}
