package com.example.owasp10_springboot.vulnerabilities;

import com.example.owasp10_springboot.controller.injection.AbstractController;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class XSSController extends AbstractController {

    @RequestMapping(value = "/xss")
    public ModelAndView process(@RequestParam(value = "string", required = false) String string, ModelAndView mav,
            Locale locale) {
        mav.setViewName("xss");
        mav.addObject("title", msg.getMessage("title.xss.page", null, locale));
        if (!StringUtils.isBlank(string)) {
            String reversedStr = StringUtils.reverse(string); // Reverse the given string
            mav.addObject("msg", msg.getMessage("label.reversed.string", null, locale) + " : " + reversedStr);
        } else {
            mav.addObject("msg", msg.getMessage("msg.enter.string", null, locale));
        }
        return mav;
    }
}