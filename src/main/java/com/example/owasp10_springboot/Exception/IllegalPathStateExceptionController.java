package com.example.owasp10_springboot.Exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.geom.GeneralPath;

@Controller
public class IllegalPathStateExceptionController {

    @RequestMapping(value = "/ipse")
    public void process() {
        GeneralPath subPath = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 100);
        subPath.closePath();
    }
}
