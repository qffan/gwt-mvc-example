/**
 * Copyright 2008-2009 by Stoned Robin
 */
package com.stonedrobin.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;

/**
 * Login form displaying user name with password and login button.
 */
public class LoginForm extends Composite implements LoginModel {
    private static final LoginConstants CONSTANTS = GWT.create(LoginConstants.class);

    private final TextBox user;
    private final PasswordTextBox password;

    public LoginForm(final LoginController controller) {
        user = new TextBox();
        password = new PasswordTextBox();

        Button logIn = new Button(CONSTANTS.getLoginButton());
        logIn.addClickListener(new ClickListener() {
            public void onClick(Widget widget) {
                controller.onLoginButtonClick(LoginForm.this);
            }
        });

        Grid grid = new Grid(2, 2);
        grid.setWidget(0, 0, new Label(CONSTANTS.getUserLabel()));
        grid.setWidget(0, 1, user);

        grid.setWidget(1, 0, new Label(CONSTANTS.getPasswordLabel()));
        grid.setWidget(1, 1, password);

        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.add(grid);
        mainPanel.add(logIn);

        initWidget(mainPanel);
    }

    public String getUser() {
        return user.getText();
    }

    public String getPassword() {
        return password.getText();
    }
}
