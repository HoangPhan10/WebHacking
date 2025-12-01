package com.example.owasp10_springboot.Exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.ByteBuffer;

@Controller
public class BufferUnderflowExceptionController {

	@RequestMapping(value = "/bue")
	public void process() {
		ByteBuffer.wrap(new byte[]{1}).getDouble();
	}
}