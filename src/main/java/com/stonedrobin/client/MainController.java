/**
 * Copyright 2009 by Google, Inc.
 */
package com.stonedrobin.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.stonedrobin.client.api.RobinServiceAsync;

public class MainController {

    private final RobinServiceAsync service;

    AsyncCallback<Boolean> callback;

    public MainController(RobinServiceAsync service,
                          final MainConstants constants, final Application application) {
        this.service = service;

        this.callback = new AsyncCallback<Boolean>() {
            public void onFailure(Throwable throwable) {
                application.displayError(constants.getSystemFailure());
            }

            public void onSuccess(Boolean success) {
                if (success) {
                    application.show(Screen.LOGIN);
                } else {
                    application.displayError(constants.getLogoutFailure());
                }
            }
        };
    }

    public void onLogoutButtonClick() {
        service.logOut(callback);
    }

}
