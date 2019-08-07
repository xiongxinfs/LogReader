package com.interview;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EventReaderTest {
    @Mock
    private EventWriter eventWriter;
    private EventReader reader;

    @Test
    public void shouldReadFileSuccessfully() {

        EventParser eventParser = new EventParserImpl();
        reader = new EventReader(eventParser, eventWriter);
        reader.readFile("log.json");

        verify(eventWriter, times(3)).write(any(Event.class));
    }
}