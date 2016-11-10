package com.vermellosa.oauth;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.extensions.appengine.auth.oauth2.AbstractAppEngineAuthorizationCodeServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Gary Cassar on 02/09/2016.
 */
public abstract class OAuthServlet extends AbstractAppEngineAuthorizationCodeServlet {

    // Would like to do this with guice but having issues
    public abstract OAuthUtils getUtils();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter respWriter = response.getWriter();
        response.setStatus(200);
        response.setContentType("text/html");
        respWriter.println("<div>Now auth-ed to access the following scopes with admin api</div>");

        for (String scope : getUtils().createScopes()) {
            respWriter.println("<li>" + scope + "</li>");
        }
        response.sendRedirect("/");

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
