package com.example.owasp10_springboot.errors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.nio.ByteBuffer;

@Controller
public class OutOfMemoryErrorController6 {

	@RequestMapping(value = "/oome6")
	public void process(ModelAndView mav) {
		mav.addObject("oome6", ByteBuffer.allocateDirect(99999999));
    }
}
