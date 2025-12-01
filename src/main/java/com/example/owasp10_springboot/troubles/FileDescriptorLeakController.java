package com.example.owasp10_springboot.troubles;

import com.example.owasp10_springboot.controller.injection.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

@Controller
public class FileDescriptorLeakController extends AbstractController {

    private static final int MAX_DISPLAY_COUNT = 15;
    private long count = 0;

    @RequestMapping(value = "/filedescriptorleak")
    public ModelAndView process(HttpServletRequest req, ModelAndView mav, Locale locale) {

        setViewAndCommonObjects(mav, locale, "filedescriptorleak");
        try {
            File file = new File(req.getServletContext().getAttribute("javax.servlet.context.tempdir").toString(),
                    "history.csv");
            FileOutputStream fos = new FileOutputStream(file, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(new Date().toString() + ",");
            osw.write(req.getRemoteAddr() + ",");
            osw.write(req.getRequestedSessionId());
            osw.write(System.getProperty("line.separator"));
            osw.flush();
            count++;

            ArrayList<String[]> history = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            long currentLineNum = 0;
            while ((line = br.readLine()) != null) {
                if (count - currentLineNum <= MAX_DISPLAY_COUNT) {
                    history.add(0, line.split(","));
                }
                currentLineNum++;
            }
            mav.addObject("history", history);

        } catch (Exception e) {
            log.error("Exception occurs: ", e);
            mav.addObject("errmsg", msg.getMessage("msg.unknown.exception.occur", null, locale));
            mav.addObject("detailmsg", e.getMessage());
        }
        return mav;
    }
}
