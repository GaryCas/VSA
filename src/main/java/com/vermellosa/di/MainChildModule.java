package com.vermellosa.di;

import com.google.appengine.api.utils.SystemProperty;
import com.google.inject.AbstractModule;

/**
 * Created by Gary Cassar on 22/08/2016.
 */
public class MainChildModule extends AbstractModule{

    @Override
    protected void configure() {

    }


    private <T> void bindLocalVsDeployment(Class<T> interfaceToBind, Class<? extends T> localBinding, Class<? extends T> deployedBinding) {
        bind(interfaceToBind).to(
                isDevelopment() ? localBinding : deployedBinding
        );
    }

    private boolean isDevelopment() {
        return SystemProperty.environment.value() == SystemProperty.Environment.Value.Development;
    }
}
