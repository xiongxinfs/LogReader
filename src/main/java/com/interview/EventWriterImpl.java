package com.interview;

public class EventWriterImpl implements EventWriter{
    HsqlDBWriter hsqlDBWriter;

    @Override
    public void init(String dbName) {
        hsqlDBWriter = new HsqlDBWriter(dbName);
        hsqlDBWriter.init();
    }

    @Override
    public void write(Event event) {
        if(hsqlDBWriter == null)
            hsqlDBWriter.init();

        String sqlStr = "INSERT INTO log VALUES (" +
                event.getId() + ", " + event.getDuration() + "," + event.getType()
                + "," + event.getHost() + "," + event.isAlert() +
                ")";

        hsqlDBWriter.invoke(sqlStr);
    }
}
