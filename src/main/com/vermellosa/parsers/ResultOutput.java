package com.vermellosa.parsers;

import java.util.Scanner;

/**
 * Created by rd019985 on 24/11/2016.
 */
public class ResultOutput {

    public static String results = "";
    public static StringBuilder sb = new StringBuilder();

    public static void createOutputFile(String content, String outputFile){
        createString(content);

        publishResults(outputFile);
    }

    private static void createString(String content){
        Scanner scanner = new Scanner(content);

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String label = line.split(",")[0];
            addOrIncrementLabel(label);
            System.out.println(line);
        }
    }

    private static void addOrIncrementLabel(String label){
        if (results.contains(label)){
            String temp = results.split(":-")[1];
            int i = Integer.parseInt(temp);
            i++;
        } else {


        }
    }

    private static void publishResults(String outputFile){
        // write to output file on CS
    }
}
