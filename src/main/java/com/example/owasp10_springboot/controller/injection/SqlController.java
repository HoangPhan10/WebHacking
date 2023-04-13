package com.example.owasp10_springboot.controller.injection;

import com.example.owasp10_springboot.JDBC.ConnectJDBC;
import com.example.owasp10_springboot.entity.User;
import com.example.owasp10_springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

@Controller
@RequestMapping("/sql-injection")
public class SqlController {
    @Autowired
    private ConnectJDBC connectJDBC;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("")
    public String sqlInjection() throws SQLException {
        String sql = "select ~0+(select 1 from ( select * from users_pvh limit 1) as a)";
        Connection connection = connectJDBC.getConnection();
        PreparedStatement statement = null;
        statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        return "injection/sql/index";
    }
}
