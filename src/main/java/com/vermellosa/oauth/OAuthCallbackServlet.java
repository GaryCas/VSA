package com.vermellosa.oauth;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.appengine.auth.oauth2.AbstractAppEngineAuthorizationCodeCallbackServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Gary Cassar on 02/09/2016.
 */
public abstract class OAuthCallbackServlet extends AbstractAppEngineAuthorizationCodeCallbackServlet {
    private static final Logger LOG = Logger.getLogger(OAuthCallbackServlet.class.getName());


    // Would like to do this with guice but having issues
    public abstract OAuthUtils getUtils();

    @Override
    protected void onSuccess(HttpServletRequest req, HttpServletResponse resp, Credential credential)
            throws ServletException, IOException {

        resp.sendRedirect("/");
    }

    @Override
    protected void onError(
            HttpServletRequest req, HttpServletResponse resp, AuthorizationCodeResponseUrl errorResponse)
            throws ServletException, IOException {
        LOG.severe("Error on callback");

        resp.setStatus(200);
        resp.addHeader("Content-Type", "text/html");
        resp.getWriter().print("<h1>Sorry, access to the admin has been denied.</h1>");
    }

    @Override
    protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
        return getUtils().getRedirectUri(req);
    }

    @Override
    protected AuthorizationCodeFlow initializeFlow() throws IOException {
        return getUtils().initialiseFlow();
    }

    @Override
    protected String getUserId(HttpServletRequest req) throws ServletException,
            IOException {

        return getUtils().getTokenReference();
    }

}
