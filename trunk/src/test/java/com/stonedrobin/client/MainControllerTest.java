/**
 * Copyright 2008-2009 by Stoned Robin
 */
package com.stonedrobin.client;

import com.stonedrobin.client.api.RobinServiceAsync;
import junit.framework.TestCase;
import static org.easymock.EasyMock.createControl;
import org.easymock.IMocksControl;

public class MainControllerTest extends TestCase {
    public void testLogoutServiceCalled() {
        IMocksControl control = createControl();

        RobinServiceAsync service = control.createMock(RobinServiceAsync.class);

        MainController controller = new MainController(service, null, null);

        service.logOut(controller.callback);

        control.replay();

        controller.onLogoutButtonClick();

        control.verify();
    }

    public void testSystemFailure() {
        IMocksControl control = createControl();

        Application application = control.createMock(Application.class);
        MainConstants constants = Messages.create(MainConstants.class);

        MainController controller = new MainController(null, constants, application);

        application.displayError(constants.getSystemFailure());

        control.replay();

        controller.callback.onFailure(null);

        control.verify();
    }

    public void testLogout_Failed() {
        IMocksControl control = createControl();

        Application application = control.createMock(Application.class);
        MainConstants constants = Messages.create(MainConstants.class);

        MainController controller = new MainController(null, constants, application);

        application.displayError(constants.getLogoutFailure());

        control.replay();

        controller.callback.onSuccess(false);

        control.verify();
    }

    public void testLogout_Successful() {
        IMocksControl control = createControl();

        Application application = control.createMock(Application.class);

        MainController controller = new MainController(null, null, application);

        application.show(Screen.LOGIN);

        control.replay();

        controller.callback.onSuccess(true);

        control.verify();
    }
}
