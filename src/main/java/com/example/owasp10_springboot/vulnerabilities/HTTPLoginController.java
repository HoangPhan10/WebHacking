package com.example.owasp10_springboot.vulnerabilities;

import com.example.owasp10_springboot.controller.injection.DefaultLoginController;
import com.sun.management.OperatingSystemMXBean;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Locale;

@Controller
public class HTTPLoginController extends DefaultLoginController {

	@Override
	@RequestMapping(value = "/status/login", method = RequestMethod.GET)
	public ModelAndView doGet(ModelAndView mav, HttpServletRequest req, HttpServletResponse res, Locale locale) {
		req.setAttribute("note", msg.getMessage("msg.note.http.login", null, locale));
		super.doGet(mav, req, res, locale);
		return mav;
	}

	@Override
	@RequestMapping(value = "/status/login", method = RequestMethod.POST)
	public ModelAndView doPost(ModelAndView mav, HttpServletRequest req, HttpServletResponse res, Locale locale) throws IOException {
		return super.doPost(mav, req, res, locale);
	}

	@RequestMapping(value = "/admins/status")
	public ModelAndView status(ModelAndView mav, Locale locale) {
		setViewAndCommonObjects(mav, locale, "status");
		mav.addObject("osMXBean", (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean());
		return mav;
	}
}
