package com.interview;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventParserImpl implements EventParser{

    private final Logger logger = LoggerFactory.getLogger(EventParserImpl.class);

    @Override
    public Event parse(String jsonEventString) {
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonEventString);
            String id = (String) jsonObject.get("id");
            Event.State state = Event.State.valueOf((String)jsonObject.get("state"));
            String type = (String) jsonObject.get("type");
            String host = (String) jsonObject.get("host");
            long timeStamp = (Long)jsonObject.get("timestamp");

            Event event = new Event(id, state, type, host, timeStamp);
            return event;
        } catch (ParseException e) {

            logger.error("Error when parsing event: ", e);
            return null;
        }
    }
}
