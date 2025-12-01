package com.example.owasp10_springboot.errors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.NetworkInterface;
import java.net.SocketException;

@Controller
public class UnsatisfiedLinkErrorController {

    private static native NetworkInterface getByName0(String name) throws SocketException;

	@RequestMapping(value = "/jnicall")
	public void process() throws SocketException {
		getByName0("");
	}
}
