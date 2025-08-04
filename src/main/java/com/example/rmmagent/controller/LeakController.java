package com.example.rmmagent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class LeakController {

    private List<String> memoryLeakList = new ArrayList<>();

    @GetMapping("/leak")
    public String createLeak() {
        for (int i = 0; i < 1000000; i++) {
            memoryLeakList.add(UUID.randomUUID().toString());
        }
        return "Memory leaking...";
    }
}
