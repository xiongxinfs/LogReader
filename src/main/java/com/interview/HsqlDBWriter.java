package com.interview;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.JDBCType.NULL;
import static java.sql.JDBCType.VARCHAR;

public class HsqlDBWriter {

    final private String dbFile;
    private Connection conn = null;

    public HsqlDBWriter(String dbFile) {
        this.dbFile = dbFile;
    }

    public Connection connect() {    // note more general exception
        try{
            Class.forName("org.hsqldb.jdbcDriver");
            conn = DriverManager.getConnection("jdbc:hsqldb:"
                        + dbFile,
                "sa",
                "");
        }  catch (Exception e) {
            e.printStackTrace(System.out);
        }
        finally {
            //conn.close();
        }
        return conn;
    }

    public void init() {
        Statement stmt = null;
        int result = 0;

        try {
            connect();
            stmt = conn.createStatement();
            result = stmt.executeUpdate("CREATE TABLE log (" +
                    "id IDENTITY NOT NULL, logid VARCHAR(50) NOT NULL" +
                    ", duration int NOT NULL, type VARCHAR(20) " +
                    ", host VARCHAR(20), alert BOOLEAN,PRIMARY KEY (id)); ");
            conn.commit();

        }  catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void invoke(String sqlStr){
        Statement stmt = null;
        int result = 0;
        try {
            stmt = conn.createStatement();
            result = stmt.executeUpdate(sqlStr);
            conn.commit();

        }  catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
