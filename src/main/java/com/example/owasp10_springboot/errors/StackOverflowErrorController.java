package com.example.owasp10_springboot.errors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StackOverflowErrorController {

	@RequestMapping(value = "/sofe")
	public void process() {
		process();
	}
}
