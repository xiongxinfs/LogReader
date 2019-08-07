package com.interview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EventLoader {

    private final EventParser eventParser;
    private final EventWriter eventWriter;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Map<String, Event> events = new HashMap<>();

    public EventLoader(EventParser eventParser, EventWriter eventWriter) {
        this.eventParser = eventParser;
        this.eventWriter = eventWriter;
    }

    public void load(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Event event = this.eventParser.parse(line);
                if(event != null && event.getId() != null){
                   Event existing = events.putIfAbsent(event.getId(), event);

                   if(existing != null){
                       updateEvent(existing, event);
                       eventWriter.write(existing);
                       events.remove(existing.getId());
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Error loading file: ", e);
        }
    }

    private void updateEvent(Event existingEvent, Event newEvent) {

        if(existingEvent.getState() == Event.State.STARTED && newEvent.getState() == Event.State.FINISHED){
            existingEvent.setDuration(newEvent.getTimeStamp() - existingEvent.getTimeStamp());
        }
        else if(existingEvent.getState() == Event.State.FINISHED && newEvent.getState() == Event.State.STARTED){
            existingEvent.setDuration(existingEvent.getTimeStamp() - newEvent.getTimeStamp());
        }
        else{
            throw new RuntimeException(String.format("Invalid log data for event: %s", existingEvent.getId()));
        }
        if(existingEvent.getDuration() > 4) {
            existingEvent.setAlert(true);
        }
    }

}
