/**
 * Copyright 2009 by Google, Inc.
 */
package com.stonedrobin.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * The main form.
 */
public class MainForm extends Composite {
    private static final MainConstants CONSTANTS = GWT.create(MainConstants.class);

    public MainForm(final MainController controller) {
        Button logOut = new Button(CONSTANTS.getLogoutButton());
        logOut.addClickListener(new ClickListener() {
            public void onClick(Widget widget) {
                controller.onLogoutButtonClick();
            }
        });
        initWidget(logOut);
    }
}
