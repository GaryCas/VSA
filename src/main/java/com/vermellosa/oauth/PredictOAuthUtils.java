package com.vermellosa.oauth;

import com.google.inject.Singleton;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gary Cassar on 02/09/2016.
 */
@Singleton
public class PredictOAuthUtils extends OAuthUtils {

    public static final String PREDICT_ADMINOAUTHSERVLET = "/admin/adminoauthservlet";
    public static final String PREDICT_OAUTH_CALLBACK_SERVLET_PATH = "/admin/adminoauthcallback";

    public static final String PREDICT_TOKEN = "PredictToken";

    protected Set<String> createScopes() {
        Set<String> localScopes = new HashSet<>();


        return Collections.unmodifiableSet(localScopes);
    }

    @Override
    public String getTokenReference() {
        return PREDICT_TOKEN;
    }

    @Override
    public String getAuthCallbackServletPath() {
        return PREDICT_OAUTH_CALLBACK_SERVLET_PATH;
    }
}
