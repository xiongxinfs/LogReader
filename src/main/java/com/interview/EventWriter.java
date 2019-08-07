package com.interview;

public interface EventWriter {
    void init(String dbName);

    void write(Event event);

}
