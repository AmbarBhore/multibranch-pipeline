package com.example.rmmagent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class DiskUsageController {

    @GetMapping("/disk-usage")
    public Map<String, String> getDiskUsage() {
        return Map.of("diskUsage", "65%");
    }
}
