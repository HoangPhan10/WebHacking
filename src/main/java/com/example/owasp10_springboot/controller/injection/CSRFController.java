package com.example.owasp10_springboot.controller.injection;

import com.example.owasp10_springboot.DTO.LoginDTO;
import com.example.owasp10_springboot.DTO.WorkDTO;
import com.example.owasp10_springboot.entity.User;
import com.example.owasp10_springboot.entity.Work;
import com.example.owasp10_springboot.entity.WorkDetail;
import com.example.owasp10_springboot.repository.UserRepository;
import com.example.owasp10_springboot.repository.WorkDetailRepository;
import com.example.owasp10_springboot.repository.WorkRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/csrf")
public class CSRFController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkRepository workRepository;
    @Autowired
    private WorkDetailRepository workDetailRepository;

    @GetMapping("")
    public String csrf() {
        return "csrf/index";
    }

    @GetMapping("/lab1")
    public String lab1(Model model, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        String cookieValue = null;

        // Tìm cookie có tên "user"
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sessionId".equals(cookie.getName())) {
                    cookieValue = cookie.getValue();
                    break;
                }
            }
        }
        if (userRepository.findUserBySessionId(cookieValue) != null) {
            Work work = workRepository.findWorkBySessionId(cookieValue);
            if (work != null) {
                List<WorkDetail> workDetails = workDetailRepository.findWorkDetailsByWorkId(work.getId());
                if (workDetails.size() > 0) {
                    model.addAttribute("works", workDetails);
                }
            }
            model.addAttribute("work", new WorkDTO());
            return "csrf/lab1/index";
        }
        return "redirect:/csrf/lab1/login";
    }

    @GetMapping("/lab1/register")
    public String lab1Register(Model model) {
        model.addAttribute("user", new LoginDTO());
        return "csrf/lab1/register";
    }

    @PostMapping("/lab1/register")
    public String createAccount(Model model, LoginDTO login) {
        User user = new User();
        user.setUsername(login.getUsername());
        user.setPassword(login.getPassword());
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString().replaceAll("-", "");
        user.setSessionId(token);
        userRepository.save(user);
        return "redirect:/csrf/lab1/login";
    }

    @GetMapping("/lab1/login")
    public String lab1Login(Model model) {
        model.addAttribute("user", new LoginDTO());
        return "csrf/lab1/login";
    }

    @PostMapping("/lab1/login")
    public String login(Model model, LoginDTO login, HttpServletResponse response) {
        User user = userRepository.findUserByUsernameAndPassword(login.getUsername(), login.getPassword());
        if (user == null) {
            return "redirect:/csrf/lab1/login";
        }
        Cookie cookie = new Cookie("sessionId", user.getSessionId());
        cookie.setHttpOnly(false);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/csrf/lab1";
    }

    @PostMapping("/lab1/works")
    public String works(Model model, WorkDTO workDTO, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        String cookieValue = null;

        // Tìm cookie có tên "user"
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sessionId".equals(cookie.getName())) {
                    cookieValue = cookie.getValue();
                    break;
                }
            }
        }
        if (userRepository.findUserBySessionId(cookieValue) != null) {
            Work work = workRepository.findWorkBySessionId(cookieValue);
            if (work != null) {
                List<WorkDetail> workDetails = work.getWorks();
                WorkDetail workDetail = new WorkDetail();
                workDetail.setMessage(workDTO.getWork());
                workDetails.add(workDetail);
                work.setWorks(workDetails);
                workDetail.setWork(work);
                workRepository.save(work);
                return "redirect:/csrf/lab1";
            }
            Work newWork = new Work();
            newWork.setSessionId(cookieValue);
            WorkDetail workDetail = new WorkDetail();
            workDetail.setMessage(workDTO.getWork());
            List<WorkDetail> works = new ArrayList<>();
            works.add(workDetail);
            newWork.setWorks(works);
            workDetail.setWork(newWork);
            workRepository.save(newWork);
            return "redirect:/csrf/lab1";
        }
        return "redirect:/csrf/lab1/login";
    }
}
