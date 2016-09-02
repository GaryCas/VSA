package com.vermellosa.oauth;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Created by Gary Cassar on 02/09/2016.
 */

@Singleton
public class PredictionOAuthCallbackServlet extends OAuthCallbackServlet{

    private static final long serialVersionUID = 2518197873017292572L;

    @Inject
    private transient PredictOAuthUtils utils;

    public PredictOAuthUtils getUtils() {
        return this.utils;
    }

}
