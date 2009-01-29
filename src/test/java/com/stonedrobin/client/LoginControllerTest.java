/**
 * Copyright 2009 by Google, Inc.
 */
package com.stonedrobin.client;

import com.stonedrobin.client.api.RobinServiceAsync;
import junit.framework.TestCase;
import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expect;
import org.easymock.IMocksControl;

public class LoginControllerTest extends TestCase {
    public void testLogin() {
        IMocksControl control = createControl();

        RobinServiceAsync service = control.createMock(RobinServiceAsync.class);

        LoginController controller = new LoginController(service, null, null);

        String user = "user";
        String password = "password";

        LoginModel model = control.createMock(LoginModel.class);
        expect(model.getUser()).andReturn(user);
        expect(model.getPassword()).andReturn(password);

        service.logIn(user, password, controller.callback);

        control.replay();

        controller.onLoginButtonClick(model);

        control.verify();
    }

    public void testSystemFailure() {
        IMocksControl control = createControl();

        Application application = control.createMock(Application.class);
        LoginConstants constants = control.createMock(LoginConstants.class);

        LoginController controller = new LoginController(null, constants, application);

        String message = "System failure";
        expect(constants.getSystemFailure()).andReturn(message);

        application.displayError(message);

        control.replay();

        controller.callback.onFailure(null);

        control.verify();
    }

    public void testLogin_Failed() {
        IMocksControl control = createControl();

        Application application = control.createMock(Application.class);
        LoginConstants constants = control.createMock(LoginConstants.class);

        LoginController controller = new LoginController(null, constants, application);

        String message = "Login failure";
        expect(constants.getLoginFailure()).andReturn(message);

        application.displayError(message);

        control.replay();

        controller.callback.onSuccess(false);

        control.verify();
    }

    public void testLogin_Successful() {
        IMocksControl control = createControl();

        Application application = control.createMock(Application.class);
        LoginConstants constants = control.createMock(LoginConstants.class);

        LoginController controller = new LoginController(null, constants, application);

        application.show(Screen.MAIN);

        control.replay();

        controller.callback.onSuccess(true);

        control.verify();
    }
}
