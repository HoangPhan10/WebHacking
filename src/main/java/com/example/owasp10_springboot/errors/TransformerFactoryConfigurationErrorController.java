package com.example.owasp10_springboot.errors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.transform.TransformerFactory;

@Controller
public class TransformerFactoryConfigurationErrorController {

	@RequestMapping(value = "/tfce")
	public void process() {
        System.setProperty("javax.xml.transform.TransformerFactory", "a");
        TransformerFactory.newInstance();
	}
}
