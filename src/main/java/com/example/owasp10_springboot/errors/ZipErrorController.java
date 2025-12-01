package com.example.owasp10_springboot.errors;

import com.example.owasp10_springboot.controller.injection.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.util.HashMap;

@Controller
public class ZipErrorController  extends AbstractController {

	@RequestMapping(value = "/ze")
	public void process(HttpServletRequest req) {
		try {
			File file = new File(req.getServletContext().getAttribute("javax.servlet.context.tempdir").toString(),
					"test.zip");
			URI uri = URI.create("jar:file:" + file.getAbsolutePath());
			file.createNewFile();
			FileSystems.newFileSystem(uri, new HashMap<String, Object>());
		} catch (IOException e) {
			log.error("IOException occurs: ", e);
		}
	}
}
