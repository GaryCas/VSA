package com.vermellosa.connectors;

import com.google.appengine.tools.cloudstorage.GcsFileOptions;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsInputChannel;
import com.google.appengine.tools.cloudstorage.GcsOutputChannel;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.appengine.repackaged.com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.appengine.repackaged.com.google.api.client.http.*;
import com.vermellosa.parsers.LabelIncParser;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.security.GeneralSecurityException;
import java.util.Collections;

/**
 * Created by User on 20/08/2016.
 */
public class CloudStorageConnector extends HttpServlet {

    public static final boolean SERVE_USING_BLOBSTORE_API = false;
    private static final int BUFFER_SIZE = 2 * 1024 * 1024;
    private static CloudStorageConnector cloudStorageConnector = null;


    private static final String STORAGE_SCOPE =
            "https://www.googleapis.com/auth/devstorage.read_write";

    private final GcsService gcsService = GcsServiceFactory.createGcsService(new RetryParams.Builder()
            .initialRetryDelayMillis(10)
            .retryMaxAttempts(10)
            .totalRetryPeriodMillis(15000)
            .build());

    public CloudStorageConnector() throws IOException {
    }

    public static CloudStorageConnector getCloudStorageConnector() throws IOException {
        if(cloudStorageConnector == null){
            cloudStorageConnector = new CloudStorageConnector();
        }
        return cloudStorageConnector;
    }

    public String getFile(String bucketName, String filename) throws IOException, GeneralSecurityException {
        String uri = "https://storage.googleapis.com/" + URLEncoder.encode(bucketName + "/" + filename, "UTF-8");

        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        HttpRequestFactory requestFactory = httpTransport.createRequestFactory(getCreds(STORAGE_SCOPE));
        GenericUrl url = new GenericUrl(uri);

        HttpRequest request = requestFactory.buildGetRequest(url);
        HttpResponse response = request.execute();
        String content = response.parseAsString();

        // make a class that constructs results

        LabelIncParser.createOutputFile(content, "quickstart-1470656086","output.txt");

        return content;

    }

    private void incrementOrAdd(String label) {
        System.out.println("Adding label " + label);
    }

    /**
     *
     * @param bucketName
     * @param outputFile
     * @throws IOException
     * @throws GeneralSecurityException
     *
     * Not sure how to get this working
     */
    public void postIt(String bucketName, String outputFile) throws IOException, GeneralSecurityException {
        String uri = URLEncoder.encode("/gcs/" + bucketName + "/" + outputFile, "UTF-8");

        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        HttpRequestFactory requestFactory = httpTransport.createRequestFactory(getCreds(STORAGE_SCOPE));
        GenericUrl url = new GenericUrl(uri);

        //doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        GcsFileOptions instance = GcsFileOptions.getDefaultInstance();
        GcsFilename fileName = getFileName(req);
        GcsOutputChannel outputChannel;
        outputChannel = gcsService.createOrReplace(fileName, instance);
        copy(req.getInputStream(), Channels.newOutputStream(outputChannel));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        GcsFilename fileName = getFileName(request);
        if (SERVE_USING_BLOBSTORE_API) {
            BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
            BlobKey blobKey = blobstoreService.createGsBlobKey(
                    "/gs/" + fileName.getBucketName() + "/" + fileName.getObjectName());
            blobstoreService.serve(blobKey, response);
        } else {
            GcsInputChannel readChannel = gcsService.openPrefetchingReadChannel(fileName, 0L, BUFFER_SIZE);
            copy(Channels.newInputStream(readChannel), response.getOutputStream());
        }
    }

    private GoogleCredential getCreds(String scopes) throws IOException {
        return GoogleCredential.getApplicationDefault()
                .createScoped(Collections.singleton(scopes));
    }

    protected GcsFilename getFileName(HttpRequest req) {

        String[] splits = req.getUrl().toString().split("/", 4);
        if (!splits[0].equals("") || !splits[1].equals("gcs")) {
            throw new IllegalArgumentException("The URL is not formed as expected. " +
                    "Expecting /gcs/<bucket>/<object>");
        }
        return new GcsFilename(splits[2], splits[3]);
    }

    protected GcsFilename getFileName(HttpServletRequest req) {

        String[] splits = req.getRequestURI().split("/", 4);
        if (!splits[0].equals("") || !splits[1].equals("gcs")) {
            throw new IllegalArgumentException("The URL is not formed as expected. " +
                    "Expecting /gcs/<bucket>/<object>");
        }
        return new GcsFilename(splits[2], splits[3]);
    }

    /**
     * Transfer the data from the inputStream to the outputStream. Then close both streams.
     */
    private void copy(InputStream input, OutputStream output) throws IOException {
        try {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = input.read(buffer);
            while (bytesRead != -1) {
                output.write(buffer, 0, bytesRead);
                bytesRead = input.read(buffer);
            }
        } finally {
            input.close();
            output.close();
        }
    }


}
