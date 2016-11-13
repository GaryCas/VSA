package com.vermellosa;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import java.lang.annotation.*;

/**
 *  This class allows Guice dependency injection in unit tests
 */
public class GuiceJUnitRunner extends BlockJUnit4ClassRunner {
    private Injector injector;

    /**
     * Make GuiceModules, available as an annotation. This will take the value of the Guice Module parameters and inject those modules as part of the class.
     */
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    public @interface GuiceModules {
        Class<?>[] value();
    }

    @Override
    public Object createTest() throws Exception {
        Object obj = super.createTest();
        injector.injectMembers(obj);
        return obj;
    }

    /**
     *
     * @param klass
     * @throws InitializationError an error that is thrown when the GuiceModules annotation contains no parameters
     *
     * Constructor:
     * Gets the modules that are specified from the "GuiceModules" parameter and creates an injector for each of them.
     */
    public GuiceJUnitRunner(Class<?> klass) throws InitializationError {
        super(klass);
        Class<?>[] classes = getModulesFor(klass);
        injector = createInjectorFor(classes);
    }

    /**
     *
     * @param classes The classes specified by the "GuiceModules" annotation
     * @return An injector for all of these classes.
     * @throws InitializationError An exception that is thrown when there are no parameters specified in the GuiceModules annotation.
     *
     * Creates an injector for each of these classes.
     * That is, a single injector for this unique combination of classes, not a single injector per class.
     */
    private Injector createInjectorFor(Class<?>[] classes) throws InitializationError {
        Module[] modules = new Module[classes.length];
        for (int i = 0; i < classes.length; i++) {
            try {
                modules[i] = (Module) (classes[i]).newInstance();
            } catch (InstantiationException e) {
                throw new InitializationError(e);
            } catch (IllegalAccessException e) {
                throw new InitializationError(e);
            }
        }
        return Guice.createInjector(modules);
    }

    /**
     *
     * @param klass
     * @return return the value of the value of the GuiceModules annotation
     * @throws InitializationError Error that is thrown when the the annotation has no parameters
     *
     * Finds the parameters of the "GuiceModules" annotation and returns the values of the classes.
     */
    private Class<?>[] getModulesFor(Class<?> klass) throws InitializationError {
        GuiceModules annotation = klass.getAnnotation(GuiceModules.class);
        if (annotation == null)
            throw new InitializationError(
                    "Missing @GuiceModules annotation for unit test '" + klass.getName()
                            + "'");
        return annotation.value();
    }
}
