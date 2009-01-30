/**
 * Copyright 2008-2009 by Stoned Robin
 */
package com.stonedrobin.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.stonedrobin.client.api.RobinServiceAsync;

public class LoginController {

    private final RobinServiceAsync service;

    AsyncCallback<Boolean> callback;

    public LoginController(RobinServiceAsync service,
                           final LoginConstants constants, final Application application) {
        this.service = service;

        this.callback = new AsyncCallback<Boolean>() {
            public void onFailure(Throwable throwable) {
                application.displayError(constants.getSystemFailure());
            }

            public void onSuccess(Boolean success) {
                if (success) {
                    application.show(Screen.MAIN);
                } else {
                    application.displayError(constants.getLoginFailure());
                }
            }
        };
    }

    public void onLoginButtonClick(LoginModel model) {
        String user = model.getUser();
        String password = model.getPassword();

        service.logIn(user, password, callback);
    }

}
