package com.example.owasp10_springboot.troubles;

import com.example.owasp10_springboot.controller.injection.AbstractController;
import com.example.owasp10_springboot.entity.UserBug;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class DBConnectionLeakController extends AbstractController {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @RequestMapping(value = "/dbconnectionleak")
    public ModelAndView process(ModelAndView mav, Locale locale) {
        setViewAndCommonObjects(mav, locale, "dbconnectionleak");
        if (StringUtils.isBlank(datasourceUrl) || datasourceUrl.startsWith("jdbc:derby:memory:")) {
            /* Just show users the warning because this feature can work if an external DB is used. */
            mav.addObject("note", msg.getMessage("msg.note.not.use.ext.db", null, locale));
            return mav;
        } else {
            mav.addObject("note", msg.getMessage("msg.note.db.connection.leak.occur", null, locale));
        }

        try {
            List<UserBug> users = selectUsers();
            if (users.isEmpty()) {
                mav.addObject("errmsg", msg.getMessage("msg.error.user.not.exist", null, locale));
            } else {
                mav.addObject("userList", users);
            }
        } catch (SQLException se) {
            log.error("SQLException occurs: ", se);
            mav.addObject("errmsg", msg.getMessage("msg.db.access.error.occur", null, locale));
        }
        return mav;
    }

    private List<UserBug> selectUsers() throws SQLException {
        List<UserBug> users = new ArrayList<>();
        Connection conn;
        Statement stmt;
        ResultSet rs;
        conn = jdbcTemplate.getDataSource().getConnection();
        stmt = conn.createStatement();
        rs = stmt.executeQuery("select id, name, phone, mail from users where ispublic = 'true'");
        while (rs.next()) {
            UserBug user = new UserBug();
            user.setUserId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPhone(rs.getString("phone"));
            user.setMail(rs.getString("mail"));
            users.add(user);
        }
        return users;
    }
}
