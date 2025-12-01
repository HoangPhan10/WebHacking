package com.example.owasp10_springboot.controller.injection;

import com.example.owasp10_springboot.DTO.CmdDTO;
import com.example.owasp10_springboot.implementService.ImplCMDInjectionService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;

@Controller
@Slf4j

public class CommandController {
    private ImplCMDInjectionService implCMDInjectionService;

    public CommandController() {
        this.implCMDInjectionService = new ImplCMDInjectionService();
    }

    @GetMapping("/command-injection")
    public String getCommandInjection() {
        return "injection/command/index";
    }

    @GetMapping("/command-injection/lab1")
    public String getCommandInjectionLab1(Model model) {
        model.addAttribute("CmdDTO", new CmdDTO(""));
        return "injection/command/lab1/index";
    }

    @GetMapping("/command-injection/lab2")
    public String getCommandInjectionLab2(Model model) {
        model.addAttribute("CmdDTO", new CmdDTO(""));
        return "injection/command/lab2/index";
    }

    @PostMapping(path = "/command-injection/lab1")
    public String postCommandInjectionLab1(CmdDTO cmdDTO, Model model) {
        model.addAttribute("CmdDTO", new CmdDTO(""));
        String regex = "^[a-zA-Z0-9 .;>/]*$";
        if (!cmdDTO.getCmd().startsWith("127.0.0.1") | !cmdDTO.getCmd().matches(regex)) {
            model.addAttribute("error", "PING Error");
            return "injection/command/lab1/index";
        }

        List<String> results = implCMDInjectionService.runShell(cmdDTO.getCmd());
        for (String str : results) {
            log.info(str);
        }
        model.addAttribute("success", "PING Successfully");
        return "injection/command/lab1/index";
    }

    @PostMapping(path = "/command-injection/lab2")
    public String postCommandInjectionLab2(CmdDTO cmdDTO, Model model) {
        log.info(cmdDTO.getCmd());
        model.addAttribute("CmdDTO", new CmdDTO(""));
        String regex = "^[a-z-A-Z0-9 .:`,=;/]*$";
        System.out.print(cmdDTO.getCmd().matches(regex));
        if (!cmdDTO.getCmd().startsWith("127.0.0.1") | !cmdDTO.getCmd().matches(regex)) {
            model.addAttribute("error", "PING Error");
            return "injection/command/lab2/index";
        }

        List<String> results = implCMDInjectionService.runShell(cmdDTO.getCmd());
        for (String str : results) {
            log.info(str);
        }
        model.addAttribute("success", "PING Successfully");
        return "injection/command/lab2/index";
    }

    @GetMapping("/")
    public String getHome() {
        return "index/index";
    }

    @GetMapping("/images")
    public void getImage(@RequestParam("filename") String filename, HttpServletResponse response) {
        try {
            String regexPhp = "^.+(\\.php)$";
            if (filename.matches(regexPhp)) {
                Process process = Runtime.getRuntime().exec("php /var/tmp/images/" + filename);
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    log.info(line);
                }
                reader.close();
            }
            String regex = "^.+(\\.jpeg|\\.jpg|\\.txt)$";
            if (filename.matches(regex)) {
                File file = new File("/var/tmp/images/" + filename);
                InputStream is = new FileInputStream(file);
                IOUtils.copy(is, response.getOutputStream());
                response.flushBuffer();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
