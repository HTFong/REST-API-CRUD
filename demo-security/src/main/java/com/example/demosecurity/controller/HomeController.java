package com.example.demosecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeController {
    @GetMapping("/contact")
    public String getContact() {
        return "Contact";
    }@GetMapping("/notices")
    public String getNotices() {
        return "Notices";
    }

}
