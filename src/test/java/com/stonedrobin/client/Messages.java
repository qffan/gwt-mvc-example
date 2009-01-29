package com.stonedrobin.client;

import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.Constants.DefaultStringValue;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Messages {

    @SuppressWarnings("unchecked")
    public static <T extends Constants> T create(Class<T> constants) {
        return (T) Proxy.newProxyInstance(constants.getClassLoader(), new Class[]{constants}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                DefaultStringValue defaultValue = method.getAnnotation(DefaultStringValue.class);
                if (defaultValue == null) {
                    throw new UnsupportedOperationException("Unsupported method " + method);
                }

                return defaultValue.value();
            }

        });
    }

}
