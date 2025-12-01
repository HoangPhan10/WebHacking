package com.example.owasp10_springboot.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Stack;

@Controller
public class EmptyStackExceptionController {

    private static final Logger log = LoggerFactory.getLogger(EmptyStackExceptionController.class);

	@RequestMapping(value = "/ese")
	public void process() {
        Stack<String> stack = new Stack<>();
        String tmp;
        while (null != (tmp = stack.pop())) {
            log.debug("Stack.pop(): {}", tmp);
        }
	}
}