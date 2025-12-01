package com.example.owasp10_springboot.Exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Controller
public class IllegalStateExceptionController {

    @RequestMapping(value = "/iase")
    public void process() {
        List<String> alphabet = new ArrayList<>(Arrays.asList("a", "b, c"));
        for (final Iterator<String> itr = alphabet.iterator(); itr.hasNext();) {
            itr.remove();
        }
    }
}
