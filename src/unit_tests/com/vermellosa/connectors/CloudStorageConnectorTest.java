package com.vermellosa.connectors;

import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

/**
 * Created by rd019985 on 18/11/2016.
 */
public class CloudStorageConnectorTest {

    @Mock
    HttpServletRequest httpServletRequest;

    @Mock
    HttpServletResponse httpServletResponse;

    @Mock
    ServletOutputStream servletOutputStream;

    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper();

    @Before
    public  void setUp(){
        MockitoAnnotations.initMocks(this);
        helper.setUp();
    }

    @After
    public void tearDown(){
        helper.tearDown();
    }

//    @Test (expected = IllegalArgumentException.class)
//    public void testGetFileWithFakeBucketName() throws IOException, GeneralSecurityException {
//        getConnector().getFile("willy");
//    }

    @Test
    public void testGetFileWithRealBucketName() throws IOException, GeneralSecurityException {
        getConnector().getFile("quickstart-1470656086", "language_id.txt");
    }


    @Test
    public void testDoPost(){
        // given

        // when

        // then
    }

    public CloudStorageConnector getConnector() throws IOException {
        return new CloudStorageConnector();
    }
}
