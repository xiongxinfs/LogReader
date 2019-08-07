package com.interview;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


public class EventWriterImplTest {

    @Test
    public void testWriteEvent() throws SQLException {

        HsqlDBClient hsqlDBClient = new HsqlDBClient("testFile");
        hsqlDBClient.init();

        EventWriter eventWriter = new EventWriterImpl(hsqlDBClient);
        String id = UUID.randomUUID().toString();
        Event event = new Event(id, Event.State.STARTED, "testType", "testHost", 12345);
        event.setDuration(6);
        event.setAlert(true);
        eventWriter.write(event);
    }

}