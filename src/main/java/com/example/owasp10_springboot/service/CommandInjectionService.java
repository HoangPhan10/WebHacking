package com.example.owasp10_springboot.service;

import java.util.List;

public interface CommandInjectionService {
    List<String> runShell(String cmd);
}
