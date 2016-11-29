package com.vermellosa.papi;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BuildModel {

    public static HttpResponse doPost() throws IOException {
        String project_id = "savvy-aileron-97711";
        String urlString = "https://www.googleapis.com/prediction/v1.6/projects/" + project_id +"/trainedmodels";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(urlString);

        // add headers like this
        //post.setHeader();

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("id", "language-identifier"));
        urlParameters.add(new BasicNameValuePair("storageDataLocation", "quickstart-1470656086/language_id.txt"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);
        System.out.println("\nSending 'POST' request to URL : " + urlString);
        System.out.println("Post parametes : " + post.getEntity());
        System.out.println("Response Code : " +
            response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result.toString());

        return response;
    }
}
