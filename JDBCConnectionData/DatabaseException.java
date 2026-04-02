package com.example.JDBCConnectionData;

import com.sun.jdi.connect.spi.Connection;

import java.sql.DriverManager;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super(message);
    }
}


class DBConnection {
    static final String URL = "jdbc:mysql://localhost:3306/hello";
    static final String USER = "root";
    static final String PASSWORD = "Nakul@123";

    public static Connection getConnection() {
        try {
            return (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new DatabaseException("Connection failed");
        }
    }
}
