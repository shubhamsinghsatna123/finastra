package com.finastra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home1")
    public String home() {
        System.out.println(" HomeController called");
        return "login";
    }
}
