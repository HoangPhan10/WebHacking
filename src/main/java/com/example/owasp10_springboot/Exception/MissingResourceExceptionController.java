package com.example.owasp10_springboot.Exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ResourceBundle;

@Controller
public class MissingResourceExceptionController {

 	@RequestMapping(value = "/mre")
	public void process() {
	    ResourceBundle.getBundle("");
	}
}