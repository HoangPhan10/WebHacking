package com.example.owasp10_springboot.troubles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

@Controller
public class JVMCrashByEAVController {

    @RequestMapping(value = "/jvmcrasheav")
    public void process() {
        try {
            getUnsafe().getByte(0);
        } catch (Exception e) {
            // do nothing
        }
    }

    private static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        Field singleoneInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
        singleoneInstanceField.setAccessible(true);
        return (Unsafe) singleoneInstanceField.get(null);
    }
}