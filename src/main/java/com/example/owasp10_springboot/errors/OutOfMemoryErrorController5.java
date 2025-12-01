package com.example.owasp10_springboot.errors;

import javassist.ClassPool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OutOfMemoryErrorController5 {

	@RequestMapping(value = "/oome5")
	public void process() {
        try {
            for (int i = 0; i < 1000000; i++) {
                ClassPool pool = ClassPool.getDefault();
                pool.makeClass("org.t246osslab.easybuggy.Generated" + i).toClass();
            }
        } catch (Exception e) {
        }
    }
}
