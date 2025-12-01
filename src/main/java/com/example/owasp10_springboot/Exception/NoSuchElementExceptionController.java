package com.example.owasp10_springboot.Exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class NoSuchElementExceptionController {

    @RequestMapping(value = "/nsee")
    public void process() {
        new ArrayList<String>().iterator().next();
    }
}
