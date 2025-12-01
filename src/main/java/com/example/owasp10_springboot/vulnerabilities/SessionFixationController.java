package com.example.owasp10_springboot.vulnerabilities;

import com.example.owasp10_springboot.controller.injection.DefaultLoginController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Locale;

@Controller
public class SessionFixationController extends DefaultLoginController {

    @Override
    @RequestMapping(value = "/sessionfixation/login", method = RequestMethod.GET)
    public ModelAndView doGet(ModelAndView mav, HttpServletRequest req, HttpServletResponse res, Locale locale) {
        mav.addObject("note", msg.getMessage("msg.note.session.fixation",
                new Object[]{ req.getRequestURL().toString() + ";jsessionid=" }, locale));
        super.doGet(mav, req, res, locale);
        return mav;
    }

    @Override
    @RequestMapping(value = "/sessionfixation/login", method = RequestMethod.POST)
    public ModelAndView doPost(ModelAndView mav, HttpServletRequest req, HttpServletResponse res, Locale locale) throws IOException {
        return super.doPost(mav, req, res, locale);
    }
}
