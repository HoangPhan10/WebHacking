package com.example.owasp10_springboot.Exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Scanner;

@Controller
public class InputMismatchExceptionController {

    @RequestMapping(value = "/ime")
    public void process() {
        try (Scanner scanner = new Scanner("a")) {
            scanner.nextInt();
        }
    }
}
