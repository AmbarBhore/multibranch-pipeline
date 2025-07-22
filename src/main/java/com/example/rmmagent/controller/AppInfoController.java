package com.example.rmmagent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class AppInfoController {

    @GetMapping("/apps")
    public List<String> getInstalledApplications() {
        // Mock installed apps
        return Arrays.asList("Google Chrome", "VS Code", "Java 17", "Slack", "Docker");
    }
}
