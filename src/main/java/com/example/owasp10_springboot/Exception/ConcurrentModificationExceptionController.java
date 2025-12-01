package com.example.owasp10_springboot.Exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class ConcurrentModificationExceptionController {

	@RequestMapping(value = "/cme")
	public void process() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        Iterator<String> iter = list.iterator();
        while (iter.hasNext()) {
            String s = iter.next();
            if ("2".equals(s)) {
                list.remove(s);
            }
        }
	}
}