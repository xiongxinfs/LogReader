package com.interview;

import java.sql.*;

public class HsqlDBClient {

    public static final String SQL = "CREATE TABLE log (" +
            "id VARCHAR(100) PRIMARY KEY" +
            ", duration int NOT NULL, type VARCHAR(20) " +
            ", host VARCHAR(20), alert BOOLEAN); ";
    private final String dbFile;
    private Connection conn = null;
    private boolean isInitialised = false;

    public HsqlDBClient(String dbFile) {
        this.dbFile = dbFile;
    }

    private void connect() throws SQLException {
        conn = DriverManager.getConnection("jdbc:hsqldb:" + dbFile,
                "sa",
                "");
    }

    public synchronized void init() throws SQLException {
        if(!isInitialised){
            connect();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(SQL);
            conn.commit();
            isInitialised = true;
        }
    }

    public int update(String sqlStr) throws SQLException {
        Statement stmt = conn.createStatement();
        int updated = stmt.executeUpdate(sqlStr);
        conn.commit();
        return updated;
    }

}
