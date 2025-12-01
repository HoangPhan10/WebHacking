package com.example.owasp10_springboot.troubles;

import com.example.owasp10_springboot.controller.injection.AbstractController;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class MojibakeController extends AbstractController {

    @RequestMapping(value = "/mojibake")
    public ModelAndView process(@RequestParam(value = "string", required = false) String string, ModelAndView mav,
    		Locale locale) {
        setViewAndCommonObjects(mav, locale, "mojibake");
        if (!StringUtils.isBlank(string)) {
            // Capitalize the given string
            String capitalizedName = WordUtils.capitalize(string);
            mav.addObject("msg", msg.getMessage("label.capitalized.string", null, locale) + " : "
                    + encodeForHTML(capitalizedName));
        } else {
            mav.addObject("msg", msg.getMessage("msg.enter.string", null, locale));
        }
        return mav;
    }
}