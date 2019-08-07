package com.interview;

import org.junit.Test;

import static org.junit.Assert.*;

public class HsqlDBWriterTest {

    @Test
    public void test() throws Exception {
        HsqlDBWriter hsqlDBWriter = new HsqlDBWriter("testDb");

        hsqlDBWriter.init();
        //hsqlDBWriter.invoke();
    }
}