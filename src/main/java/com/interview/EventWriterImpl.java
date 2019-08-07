package com.interview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class EventWriterImpl implements EventWriter{
    private final HsqlDBClient hsqlDBClient;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public EventWriterImpl(HsqlDBClient hsqlDBClient) {
        this.hsqlDBClient = hsqlDBClient;
    }

    @Override
    public void write(Event event) {
        if(hsqlDBClient != null){
            String sql = String.format("INSERT INTO log VALUES ( %s, %s, %s, %s, %s )",
                    event.getId(), event.getDuration(), event.getType(), event.getHost(), event.isAlert());
            try {
                hsqlDBClient.update(sql);
            } catch (SQLException e) {
                logger.error("Error writing event:", e);
            }
        }
    }
}
