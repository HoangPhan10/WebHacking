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
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String url = "jdbc:mysql://35.198.246.16:3308/demo?createDatabaseIfNotExist=true";
            String username = "root";
            String password = "Hoang0339141241.";
            connection = DriverManager.getConnection(url,username,password);

        }catch (Exception exception){
            exception.printStackTrace();
        }
        return connection;
    }
}
