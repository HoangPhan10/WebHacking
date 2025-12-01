package com.example.owasp10_springboot.errors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Properties;
import java.util.Random;

@Controller
public class OutOfMemoryErrorController4 {

	@RequestMapping(value = "/oome4")
	public void process() {
		Properties properties = System.getProperties();
		Random r = new Random();
		while (true) {
			properties.put(r.nextInt(), "value");
		}
	}
}
