/**
 * Copyright 2008-2009 by Stoned Robin
 */
package com.stonedrobin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.stonedrobin.client.api.RobinService;
import com.stonedrobin.client.api.RobinServiceAsync;

import java.util.HashMap;
import java.util.Map;

/**
 * Main entry point.
 */
public class MainModule implements EntryPoint, Application {
    /**
     * All known screens.
     */
    private Map<Screen, Widget> screens = new HashMap<Screen, Widget>();

    public void onModuleLoad() {
        LoginForm loginForm = new LoginForm(new LoginController(
                GWT.<RobinServiceAsync>create(RobinService.class),
                GWT.<LoginConstants>create(LoginConstants.class),
                this
        ));

        screens.put(Screen.LOGIN, loginForm);

        MainForm mainForm = new MainForm(new MainController(
                GWT.<RobinServiceAsync>create(RobinService.class),
                GWT.<MainConstants>create(MainConstants.class),
                this
        ));

        screens.put(Screen.MAIN, mainForm);

        RootPanel.get().add(loginForm);
    }

    public void show(Screen screen) {
        Widget widget = screens.get(screen);

        RootPanel root = RootPanel.get();
        root.clear();
        root.add(widget);
    }

    public void displayError(String message) {
        Window.alert(message);
    }
}
										