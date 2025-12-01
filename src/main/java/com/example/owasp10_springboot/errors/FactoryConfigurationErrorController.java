package com.example.owasp10_springboot.errors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.parsers.SAXParserFactory;

@Controller
public class FactoryConfigurationErrorController {

	@RequestMapping(value = "/fce")
	public void process() {
		System.setProperty("javax.xml.parsers.SAXParserFactory", "non-exist-factory");
		SAXParserFactory.newInstance();
	}
}
