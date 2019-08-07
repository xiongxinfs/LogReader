package com.interview;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EventReader {

    private final EventParser eventParser;
    private final EventWriter eventWriter;

    private Map<String, Event> events = new HashMap<>();

    public EventReader(EventParser eventParser, EventWriter eventWriter) {
        this.eventParser = eventParser;
        this.eventWriter = eventWriter;
    }

    public void readFile(String filePath) {
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
            e.printStackTrace();
        }
    }

    private void updateEvent(Event existingEvent, Event newEvent) {

        long duration = 0;
        if(existingEvent.getState() == Event.State.STARTED && newEvent.getState() == Event.State.FINISHED){
            //existingEvent.setDuration(newEvent.getTimeStamp() - existingEvent.getTimeStamp());
            duration = newEvent.getTimeStamp() - existingEvent.getTimeStamp();
        }
        else if(existingEvent.getState() == Event.State.FINISHED && newEvent.getState() == Event.State.STARTED){
            //existingEvent.setDuration(existingEvent.getTimeStamp() - newEvent.getTimeStamp());
            duration = existingEvent.getTimeStamp() - newEvent.getTimeStamp();
        }
        else{
            throw new RuntimeException(String.format("Invalid log data for event: %s", existingEvent.getId()));
        }
        if(duration > 0) {
            existingEvent.setDuration(duration);
            existingEvent.setAlert(duration >= 4 ? true : false);
        }
    }

}
