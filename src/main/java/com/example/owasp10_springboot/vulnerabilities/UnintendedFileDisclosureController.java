package com.example.owasp10_springboot.vulnerabilities;

import com.example.owasp10_springboot.controller.injection.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class UnintendedFileDisclosureController extends AbstractController {

    @RequestMapping(value = "/unintendedfiledisclosure")
    public ModelAndView unintendedfiledisclosure(ModelAndView mav, HttpServletRequest req, Locale locale) {
        setViewAndCommonObjects(mav, locale, "unintendedfiledisclosure");
        String baseURL = req.getRequestURL().toString().replaceAll("/unintendedfiledisclosure*.+", "");
        String[] placeholders = new String[]{ baseURL};
        mav.addObject("note", msg.getMessage("msg.note.unintendedfiledisclosure", placeholders, locale));
        return mav;
    }
}