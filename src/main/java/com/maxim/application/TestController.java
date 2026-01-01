package com.maxim.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello(){
        return "HelloWorld"; // new String("HelloWorld");
    }

    @GetMapping("/helloList")
    public Collection<String> helloList(){
        return List.of("HelloWorld");
    }
}
