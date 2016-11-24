package com.vermellosa.parameterised_tests;

import com.vermellosa.connectors.CloudStorageConnector;

import java.io.IOException;

/**
 * Created by rd019985 on 24/11/2016.
 */
public class GetConnectors {

    public static CloudStorageConnector getCloudStorageConnector() throws IOException {
        return new CloudStorageConnector();
    }
}
