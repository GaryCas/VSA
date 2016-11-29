package com.vermellosa.parsers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by rd019985 on 29/11/2016.
 */
public class ResultsParser {

    public static String parsePredictionOutput(String path){
        BufferedReader br = null;

        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader(path));
            String[] temp;

            while ((sCurrentLine = br.readLine()) != null) {
                temp = sCurrentLine.split(":");
                if(temp[0].contains("outputLabel")){
                    return temp[1].substring(2, temp[1].length() - 2);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return "";
    }
}
