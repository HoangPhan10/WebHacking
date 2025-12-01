package com.example.owasp10_springboot.implementService;

import com.example.owasp10_springboot.service.CommandInjectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ImplCMDInjectionService implements CommandInjectionService {
    @Override
    public List<String> runShell(String cmd) {
        List<String> result = new ArrayList<>();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("bash", "-c", "ping -c 1 " + cmd);
            Process process = processBuilder.start();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                result.add(s);
            }
        }
        catch (IOException e) {
            log.info("Error executing command");
        }
        return result;
    }
}
