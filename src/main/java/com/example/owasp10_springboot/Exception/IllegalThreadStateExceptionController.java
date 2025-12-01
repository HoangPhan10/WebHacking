package com.example.owasp10_springboot.Exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class IllegalThreadStateExceptionController {

    @RequestMapping(value = "/itse")
    public void process() {
        Runtime rt = Runtime.getRuntime();
		try {
			Process proc = rt.exec("javac");
	        proc.exitValue();
		} catch (IOException e) {
		}
    }
}
