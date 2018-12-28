package com.supermarket.util.data.manipulation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.supermarket.util.data.connection.ServerConnection.getConnection;

public class ServerManipulation {
    public static ResultSet execQuery(String sqlQuery) throws SQLException {
        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(sqlQuery);
        return rs;
    }
    public static int nonRsExecQuery1(String sqlQuery) throws SQLException {
        Statement stmt = getConnection().createStatement();
        int rs = stmt.executeUpdate(sqlQuery);
        return rs;
    }

    public static void nonRsExecQuery(String sqlQuery) throws SQLException {
        Statement stmt = getConnection().createStatement();
        int rs = stmt.executeUpdate(sqlQuery);

    }
}