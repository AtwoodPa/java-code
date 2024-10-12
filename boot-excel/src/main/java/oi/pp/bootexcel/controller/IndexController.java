package oi.pp.bootexcel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/")
    public String hello() {
        return "Hello Hello Hello Hello";
    }

    @GetMapping("/index")
    public String index() {
        return "Hello World";
    }

}
