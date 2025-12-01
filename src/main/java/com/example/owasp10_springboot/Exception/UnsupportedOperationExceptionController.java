package com.example.owasp10_springboot.Exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Controller
public class UnsupportedOperationExceptionController {

    @RequestMapping(value = "/uoe")
    public void process() {
        List<String> alphabet = Arrays.asList("a", "b", "c");
        Iterator<String> i = alphabet.iterator();
        while(i.hasNext()){
            String name = i.next();
            if(!"a".equals(name)){
                i.remove();
            }
        }
    }
}
