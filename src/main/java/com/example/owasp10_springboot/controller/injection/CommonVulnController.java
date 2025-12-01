package com.example.owasp10_springboot.controller.injection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class CommonVulnController {
    @GetMapping("/common-vuln")
    public String getCommandInjection() {
        return "common-vuln/index";
    }
    @GetMapping("/troubles")
    public String getTroubles() {
        return "troubles/index";
    }
    @GetMapping("/exceptions")
    public String getExceptions() {
        return "exception/index";
    }
    @GetMapping("/errors")
    public String getErrors() {
        return "errors/index";
    }
}
