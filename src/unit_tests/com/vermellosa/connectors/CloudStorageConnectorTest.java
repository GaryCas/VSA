package com.vermellosa.connectors;

import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.vermellosa.parameterised_tests.GetConnectors;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
//        getCloudStorageConnector().getFile("willy");
//    }

    @Test
    public void testGetFileWithRealBucketName() throws IOException, GeneralSecurityException {
        GetConnectors.getCloudStorageConnector().getFile("quickstart-1470656086", "language_id.txt");
    }


    @Test
    public void testDoPost(){
        // given

        // when

        // then
    }


}
