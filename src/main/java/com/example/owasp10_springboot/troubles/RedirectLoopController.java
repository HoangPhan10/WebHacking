package com.example.owasp10_springboot.troubles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class RedirectLoopController {

    @RequestMapping(value = "/redirectloop")
    public String process() throws IOException {
        return "redirect:redirectloop";
    }
}