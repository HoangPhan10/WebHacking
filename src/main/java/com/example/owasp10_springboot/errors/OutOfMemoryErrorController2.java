package com.example.owasp10_springboot.errors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OutOfMemoryErrorController2 {

	@RequestMapping(value = "/oome2")
	public void process(ModelAndView mav) {
		mav.addObject("oome2", new byte[Integer.MAX_VALUE]);
	}
}
