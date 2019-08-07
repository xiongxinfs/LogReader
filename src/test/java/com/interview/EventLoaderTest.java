package com.interview;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EventLoaderTest {
    @Mock
    private EventWriter eventWriter;
    private EventLoader reader;

    @Test
    public void shouldLoadFileSuccessfully() {

        EventParser eventParser = new EventParserImpl();
        reader = new EventLoader(eventParser, eventWriter);
        reader.load("log.json");

        verify(eventWriter, times(3)).write(any(Event.class));
    }
}