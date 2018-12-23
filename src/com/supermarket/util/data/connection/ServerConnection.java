package com.supermarket.util.data.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServerConnection {
    public static Connection getConnection() throws SQLException {
        String connectionUrl = "jdbc:sqlserver://localhost;databaseName=supermarket;user=sa;password=123";
        Connection con = DriverManager.getConnection(connectionUrl);
        return con;
    }


    //     Test Snippet
//     while (rs.next()) {
//        System.out.println(rs.getString("staff_name") + " " + rs.getString("sex"));
//    }
}
