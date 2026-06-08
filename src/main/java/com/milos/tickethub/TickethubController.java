package com.milos.tickethub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TickethubController {

    @GetMapping("/")
    public String hello(){
        return "Hello world!";
    }
}
