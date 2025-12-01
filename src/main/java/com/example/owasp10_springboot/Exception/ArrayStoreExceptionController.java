package com.example.owasp10_springboot.Exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ArrayStoreExceptionController {

	@RequestMapping(value = "/ase")
	public void process() {
		Object[] objects = new String[1];
		objects[0] = Integer.valueOf(1);
	}
}