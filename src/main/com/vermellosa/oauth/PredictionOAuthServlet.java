package com.vermellosa.oauth;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Created by User on 20/08/2016.
 */
@Singleton
public class PredictionOAuthServlet {
    private static final long serialVersionUID = 2518197873017292572L;

    @Inject
    private transient PredictOAuthUtils utils;

    public PredictOAuthUtils getUtils() {
        return this.utils;
    }

}
