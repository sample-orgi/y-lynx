package com.trss.bi.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWorldResource {
    @GetMapping("/hello-world")
    public String getHelloWorld() {
        return "success";
    }
}
