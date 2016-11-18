package com.vermellosa.utils;

import com.google.appengine.api.appidentity.AppIdentityService;
import com.google.appengine.api.appidentity.AppIdentityServiceFactory;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GoogleServiceWrapper {

    public BlobstoreService getBlobstoreService() {
        return BlobstoreServiceFactory.getBlobstoreService();
    }

    public AppIdentityService getAppIdentityService() {
        return AppIdentityServiceFactory.getAppIdentityService();
    }

    public GcsService getCloudStorageService() {
        return GcsServiceFactory.createGcsService();
    }

    public Queue getQueue(String queueName) {
        return QueueFactory.getQueue(queueName);
    }

    public URLFetchService getFetchService() {
        return URLFetchServiceFactory.getURLFetchService();
    }

    public GoogleCredential getDefaultCredential() throws IOException {
        return GoogleCredential.getApplicationDefault();
    }

    public Connection getConnection(String url) throws SQLException {
        return DriverManager.getConnection(url);
    }
}
