package com.example.rmmagent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;

@RestController
public class UptimeController {

    @GetMapping("/uptime")
    public String getUptime() {
        long uptimeMillis = ManagementFactory.getRuntimeMXBean().getUptime();
        long uptimeSeconds = uptimeMillis / 1000;
        return "System uptime: " + uptimeSeconds + " seconds";
    }
}
