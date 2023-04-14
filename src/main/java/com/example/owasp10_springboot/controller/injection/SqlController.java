package com.example.owasp10_springboot.controller.injection;

import com.example.owasp10_springboot.DTO.LoginDTO;
import com.example.owasp10_springboot.JDBC.ConnectJDBC;
import com.example.owasp10_springboot.entity.User;
import com.example.owasp10_springboot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@Slf4j
@RequestMapping("/sql-injection")
public class SqlController {
    @Autowired
    private ConnectJDBC connectJDBC;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("")
    public String sqlInjection() throws SQLException {
        return "injection/sql/index";
    }

    @GetMapping("/lab1")
    public String getSqlInjectionLab1(Model model){
        model.addAttribute("model",new LoginDTO());
        return "injection/sql/lab1/index";
    }
    @PostMapping ("/lab1")
    public String postSqlInjectionLab1(Model model, LoginDTO inputDTO) throws SQLException {
        final String regex = "sleep|if|column|null|when|then|case|else|substr|group|join";
        String sql = MessageFormat.format("SELECT password_flag FROM users_hack WHERE username_ctf =''{0}''and password_flag=''{1}'' ",inputDTO.getUsername(),inputDTO.getPassword());
        final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(sql);
         if(matcher.find()){
            model.addAttribute("model",new LoginDTO());
            model.addAttribute("error","No hacker");
            return "injection/sql/lab1/index";
        }
        Connection connection = connectJDBC.getConnection();
        PreparedStatement statement = null;
        statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            return "injection/sql/lab1/home";
        }
        model.addAttribute("model",new LoginDTO());
        model.addAttribute("error","Username or password incorrect");
        return "injection/sql/lab1/index";
    }
    @GetMapping("/lab2")
    public String getSqlInjectionLab2(Model model){
        model.addAttribute("model",new LoginDTO());
        return "injection/sql/lab2/index";
    }
    @PostMapping ("/lab2")
    public String postSqlInjectionLab2(Model model, LoginDTO inputDTO) throws SQLException {
        final String regex = "sleep|if|column|null|when|then|case|else|substr|group|limit|~|exp";
        String sql = MessageFormat.format("SELECT password_flag FROM users_hack WHERE username_ctf =''{0}''and password_flag=''{1}'' ",inputDTO.getUsername(),inputDTO.getPassword());
        final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(sql);
        if(matcher.find()){
            model.addAttribute("model",new LoginDTO());
            model.addAttribute("error","No hacker");
            return "injection/sql/lab2/index";
        }
        Connection connection = connectJDBC.getConnection();
        PreparedStatement statement = null;
        statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            return "injection/sql/lab2/home";
        }
        model.addAttribute("model",new LoginDTO());
        model.addAttribute("error","Username or password incorrect");
        return "injection/sql/lab2/index";
    }

}
