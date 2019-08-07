package com.interview;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class EventParserImplTest {

    private EventParser eventParser;

    @Before
    public void setUp() throws Exception {
        eventParser = new EventParserImpl();
    }


    @Test
    public void shouldParseSuccessfully(){

        String testStr = "{\"id\":\"scsmbstgra\", \"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\", \"host\":\"12345\", \"timestamp\":1491377495212}\n";
        Event event = eventParser.parse(testStr);

        assertThat(event.getId(), is("scsmbstgra"));

    }


}