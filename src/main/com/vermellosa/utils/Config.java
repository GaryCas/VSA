package com.vermellosa.utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.net.URL;
import java.util.List;

/**
 * Created by Gary Cassar on 02/09/2016.
 */
public class Config {

    private static volatile Config singleton;
    private static PropertiesConfiguration config;

    private Config() throws ConfigurationException {
        this.loadPropertiesFromFile("config.properties");
    }

    protected static Config getInstance() {
        if(singleton == null) {
            PropertiesConfiguration var0 = config;
            synchronized(config) {
                if(singleton == null) {
                    try {
                        singleton = new Config();
                    } catch (ConfigurationException var3) {
                        throw new RuntimeException(var3);
                    }
                }
            }

        }

        return singleton;
    }

    private void loadPropertiesFromFile(String fileName) throws ConfigurationException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(fileName);
        config.setListDelimiter(';');
        config.setURL(url);
        config.load();
    }

    public static String getProperty(String key) {
        return getInstance().getConfig().getString(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return getInstance().getConfig().getString(key, defaultValue);
    }

    public static Integer getIntegerProperty(String key, Integer defaultValue) {
        return getInstance().getConfig().getInteger(key, defaultValue);
    }

    public static Double getDoubleProperty(String key, Double defaultValue) {
        return getInstance().getConfig().getDouble(key, defaultValue);
    }

    public static Long getLongProperty(String key, Long defaultValue) {
        return getInstance().getConfig().getLong(key, defaultValue);
    }

    public static Boolean getBooleanProperty(String key, Boolean defaultValue) {
        return getInstance().getConfig().getBoolean(key, defaultValue);
    }

    public static List getList(String key) {
        return getInstance().getConfig().getList(key);
    }



    protected PropertiesConfiguration getConfig() {
        return config;
    }


}
