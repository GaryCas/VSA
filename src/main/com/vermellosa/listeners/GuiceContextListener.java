package com.vermellosa.listeners;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.vermellosa.di.MainChildModule;
import com.vermellosa.utils.OfyRegi;

import javax.servlet.ServletContextEvent;
import java.util.logging.Logger;

/**
 * Created by Gary Cassar on 02/09/2016.
 */
public class GuiceContextListener extends GuiceServletContextListener{

    private static final Logger LOG = Logger.getLogger(GuiceContextListener.class.getName());
    private Injector injector;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        OfyRegi.registerObjectifyModels();

        LOG.info("Context created");
    }

    @Override
    protected Injector getInjector() {
        injector = Guice.createInjector(
                new MainChildModule()
        );
        return injector;
    }
}
