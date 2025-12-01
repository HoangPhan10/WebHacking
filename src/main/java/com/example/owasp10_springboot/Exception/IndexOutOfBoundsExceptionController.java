package com.example.owasp10_springboot.Exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class IndexOutOfBoundsExceptionController {

    @RequestMapping(value = "/ioobe")
    public void process() {
        new ArrayList<String>().get(1);
    }
}
