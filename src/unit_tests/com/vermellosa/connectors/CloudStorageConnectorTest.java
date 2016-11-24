package com.vermellosa.connectors;

import org.junit.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * Created by rd019985 on 18/11/2016.
 */
public class CloudStorageConnectorTest {

    @Test (expected = IllegalArgumentException.class)
    public void testGetFileWithFakeBucketName() throws IOException, GeneralSecurityException {
        getConnector().getFile("willy");
    }

    @Test
    public void testGetFileWithRealBucketName() throws IOException, GeneralSecurityException {
        getConnector().getFile("quickstart-1470656086");
    }

    public CloudStorageConnector getConnector() throws IOException {
        return new CloudStorageConnector();
    }
}
