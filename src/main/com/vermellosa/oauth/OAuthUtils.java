package com.vermellosa.oauth;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.appengine.datastore.AppEngineDataStoreFactory;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.jackson2.JacksonFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Set;

/**
 * Created by Gary Cassar on 02/09/2016.
 */
public abstract class OAuthUtils {
    public static final UrlFetchTransport HTTP_TRANSPORT = new UrlFetchTransport();
    public static final JacksonFactory JSON_FACTORY = new JacksonFactory();
    private OAuthUtils.Builder builder;

    public GoogleAuthorizationCodeFlow initialiseFlow() throws IOException {

        // this is not tested. It should be.
        GoogleAuthorizationCodeFlow.Builder flowBuilder = getBuilder().getFlowBuilder();

        flowBuilder.setApprovalPrompt("force");
        flowBuilder.setDataStoreFactory(AppEngineDataStoreFactory.getDefaultInstance());
        flowBuilder.setAccessType("offline");

        return flowBuilder.build();
    }


    protected String getClientId() {
        return "";
//        return Config.getProperty(Constants.CLIENT_ID_KEY);
    }

    protected String getClientSecret() {
        return "";
//         return Config.getProperty(Constants.CLIENT_SECRET_KEY);
    }

    public String getRedirectUri(HttpServletRequest req) {
        GenericUrl url = new GenericUrl(req.getRequestURL().toString());
        url.setRawPath(getAuthCallbackServletPath());
        return url.build();
    }

    public Credential getCredential() throws IOException {
        return initialiseFlow().loadCredential(getTokenReference());
    }

    OAuthUtils.Builder getBuilder(){
        if(builder == null){
            builder = new OAuthUtils.Builder();
        }
        return builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    protected abstract Set<String> createScopes();

    public abstract String getTokenReference();

    public abstract String getAuthCallbackServletPath();

    public class Builder{
        public Builder(){

        }

        public GoogleAuthorizationCodeFlow.Builder getFlowBuilder() {
            return new GoogleAuthorizationCodeFlow.Builder(
                    HTTP_TRANSPORT,
                    JSON_FACTORY,
                    getClientId(),
                    getClientSecret(),
                    createScopes());
        }
    }
}
