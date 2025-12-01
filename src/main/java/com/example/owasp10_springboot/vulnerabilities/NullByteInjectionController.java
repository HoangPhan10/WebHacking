package com.example.owasp10_springboot.vulnerabilities;

import com.example.owasp10_springboot.controller.injection.AbstractController;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

@Controller
public class NullByteInjectionController extends AbstractController {

	@RequestMapping(value = "/nullbyteijct")
    public ModelAndView process(ModelAndView mav, HttpServletRequest req, HttpServletResponse res, Locale locale) throws IOException {
        setViewAndCommonObjects(mav, locale, "nullbyteinjection");
		String fileName = req.getParameter("fileName");
		if (StringUtils.isBlank(fileName)) {
			return mav;
		} else {
			fileName = fileName + ".pdf";
		}
		Resource resource = new ClassPathResource("/pdf/" + fileName);
		try (InputStream fis = resource.getInputStream(); ServletOutputStream os = res.getOutputStream()) {
			String mimeType = req.getServletContext().getMimeType(resource.getURI().getPath());
			res.setContentType(mimeType != null ? mimeType : "application/octet-stream");
			res.setContentLength((int) resource.contentLength());
			res.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			FileCopyUtils.copy(fis, os);
		} catch (Exception e) {
			log.error("Exception occurs: ", e);
		}
		return null;
	}
}
