package com.example.ThymeLeafProject.Controller;


import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("message","hello");
        return "hello";


    }
}
