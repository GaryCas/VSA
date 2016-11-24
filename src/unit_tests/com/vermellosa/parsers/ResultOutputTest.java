package com.vermellosa.parsers;

import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.vermellosa.parameterised_tests.GetConnectors;
import com.vermellosa.parameterised_tests.StringProvider;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by rd019985 on 24/11/2016.
 */
public class ResultOutputTest {

    LocalServiceTestHelper helper = new LocalServiceTestHelper();

    @Before
    public void setUp(){
        helper.setUp();
    }

    @After
    public void tearDown(){
        helper.tearDown();
    }

    @Test
    public void addOrIncrementLabelTest(){
        // given
        setResults();

        // when
        ResultOutput.addOrIncrementLabel("Happy");
        assertEquals( ResultOutput.results.get(0).split(":- ")[1], "4");
        assertEquals( ResultOutput.results.size(), 3);

        ResultOutput.addOrIncrementLabel("Happy");
        assertEquals( ResultOutput.results.get(0).split(" :- ")[1], "5");
        assertEquals( ResultOutput.results.size(), 3);

        ResultOutput.addOrIncrementLabel("Sad");
        assertEquals( ResultOutput.results.get(1).split(" :- ")[1], "3");
        assertEquals( ResultOutput.results.size(), 3);

        ResultOutput.addOrIncrementLabel("Meh");
        assertEquals( ResultOutput.results.get(2).split(" :- ")[1], "2");
        assertEquals( ResultOutput.results.size(), 3);

        ResultOutput.addOrIncrementLabel("NothingAtAll:(");
        assertEquals( ResultOutput.results.get(3).split(" :- ")[0], "NothingAtAll:(");
        assertEquals( ResultOutput.results.get(3).split(" :- ")[1], "1");
        assertEquals( ResultOutput.results.size(), 4);
    }

    private void setResults() {
        ResultOutput.results = new ArrayList<>();
        ResultOutput.results.add("Happy :- 3");
        ResultOutput.results.add("Sad :- 2");
        ResultOutput.results.add("Meh :- 1");
    }

    @Test
    public void testPublishResults() throws IOException, GeneralSecurityException {
        // given
        setResults();

        // when
        ResultOutput.publishResults("unitTests.txt");

        // then
        String file = GetConnectors.getCloudStorageConnector().getFile("quickstart-1470656086", "unitTests.txt");
        assertNotNull(file);
        assertNotEquals(file, "");
    }
}
