package com.vermellosa.parsers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResultsParserTest {

    @Test
    public void testParser(){
        String results = ResultsParser.parsePredictionOutput("C:\\Users\\rd019985\\OneDrive\\VSA\\VSA\\src\\testResources\\predictionOutputSample.txt");

        assertEquals(results, "French");
    }
}
