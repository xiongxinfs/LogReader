package com.interview;

import java.sql.SQLException;

public class EventLoggerApp {

    public static void main(String[] args) throws SQLException {

        HsqlDBClient hsqlDBClient = new HsqlDBClient("testFile");
        hsqlDBClient.init();
        EventWriter eventWriter = new EventWriterImpl(hsqlDBClient);
        EventLoader eventLoader = new EventLoader(new EventParserImpl(), eventWriter);
        eventLoader.load("log.json");

    }
}
