package com.example.owasp10_springboot.troubles;

import jakarta.servlet.ServletException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class ForwardLoopController {

    @RequestMapping(value = "/forwardloop")
    public String process() throws IOException, ServletException {
    	return "forward:forwardloop";
    }
}