package com.vermellosa.parsers;

import com.vermellosa.connectors.CloudStorageConnector;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by rd019985 on 24/11/2016.
 */
public class LabelIncParser {

    public static ArrayList<String> results = new ArrayList<>();
    public static StringBuilder sb = new StringBuilder();


    public static void createOutputFile(String content, String bucketName, String outputFile){
        createString(content);

        try {
            CloudStorageConnector.getCloudStorageConnector().postIt(bucketName, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param content A string result of the input file that comes the prediction api
     *
     * Generates labels and a numerical value according to their frequency
     */
    protected static void createString(String content){
        Scanner scanner = new Scanner(content);

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String label = line.split(",")[0];
            addOrIncrementLabel(label);
        }
    }

    protected static void addOrIncrementLabel(String label){
        // will overwrite the field
        int labelIndex = resultsHas(label);
        if (labelIndex != -1){
            String temp = results.get(labelIndex).split(":- ")[1];
            int i = Integer.parseInt(temp);
            i++;
            results.add(labelIndex, label + " :- " + String.valueOf(i));
            results.remove(labelIndex +1 );
        } else {
            results.add(label + " :- 1");
        }
    }

    private static int resultsHas(String label) {
       for(int i = 0; i < results.size(); i++){
            if(results.get(i).split(" :- ")[0].contains(label)){
                return i;
            }
       }
        return -1;
    }


}
