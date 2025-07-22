package com.example.rmmagent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestHangController {

    @GetMapping("/freeze")
    public String freeze() {
        return "Freeze endpoint called, but no hang is simulated anymore.";
    }

    @GetMapping("/healthz")
    public String healthz() {
        return "OK";
    }
}
