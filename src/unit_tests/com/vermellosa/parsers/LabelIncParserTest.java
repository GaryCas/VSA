package com.vermellosa.parsers;

import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by rd019985 on 24/11/2016.
 */
public class LabelIncParserTest {

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
        LabelIncParser.addOrIncrementLabel("Happy");
        assertEquals( LabelIncParser.results.get(0).split(":- ")[1], "4");
        assertEquals( LabelIncParser.results.size(), 3);

        LabelIncParser.addOrIncrementLabel("Happy");
        assertEquals( LabelIncParser.results.get(0).split(" :- ")[1], "5");
        assertEquals( LabelIncParser.results.size(), 3);

        LabelIncParser.addOrIncrementLabel("Sad");
        assertEquals( LabelIncParser.results.get(1).split(" :- ")[1], "3");
        assertEquals( LabelIncParser.results.size(), 3);

        LabelIncParser.addOrIncrementLabel("Meh");
        assertEquals( LabelIncParser.results.get(2).split(" :- ")[1], "2");
        assertEquals( LabelIncParser.results.size(), 3);

        LabelIncParser.addOrIncrementLabel("NothingAtAll:(");
        assertEquals( LabelIncParser.results.get(3).split(" :- ")[0], "NothingAtAll:(");
        assertEquals( LabelIncParser.results.get(3).split(" :- ")[1], "1");
        assertEquals( LabelIncParser.results.size(), 4);
    }

    private void setResults() {
        LabelIncParser.results = new ArrayList<>();
        LabelIncParser.results.add("Happy :- 3");
        LabelIncParser.results.add("Sad :- 2");
        LabelIncParser.results.add("Meh :- 1");
    }

}
