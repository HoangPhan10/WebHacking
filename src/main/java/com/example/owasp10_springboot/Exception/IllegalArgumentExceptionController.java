package com.example.owasp10_springboot.Exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class IllegalArgumentExceptionController {

    @RequestMapping(value = "/iae")
    public void process(ModelAndView mav) {
        mav.addObject(new ArrayList<Object>(-1));
    }
}
