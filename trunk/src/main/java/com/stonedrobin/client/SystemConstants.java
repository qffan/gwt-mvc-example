package com.stonedrobin.client;

import com.google.gwt.i18n.client.Constants;

public interface SystemConstants extends Constants {

    @DefaultStringValue("Things has gone horribly wrong. Please retry.")
    public abstract String getSystemFailure();

}