package com.example.owasp10_springboot.Exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

@Controller
public class ImagingOpExceptionController {

    @RequestMapping(value = "/imoe")
    public void process() {
        BufferedImage img = new BufferedImage(1, 40000, BufferedImage.TYPE_INT_RGB);
        AffineTransformOp flipAtop = new AffineTransformOp(AffineTransform.getScaleInstance(1, 1),
                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        flipAtop.filter(img, null);
    }
}
