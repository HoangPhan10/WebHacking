package com.example.owasp10_springboot.JDBC;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class ConnectJDBC {
    public Connection getConnection(){
        Connection connection = null;
        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            String url = "jdbc:mysql://localhost:3306/owasp10?createDatabaseIfNotExist=true";
            String username = "phanhoang";
            String password = "10022002";
            connection = DriverManager.getConnection(url,username,password);

        }catch (Exception exception){
            exception.printStackTrace();
        }
        return connection;
    }
}
