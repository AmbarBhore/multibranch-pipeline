package com.example.rmmagent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class LeakController {

    // Hold memory here across requests
    private List<byte[]> memoryLeakList = new ArrayList<>();

    @GetMapping("/leak")
    public String createLeak() {
        try {
            // Allocate 50 MB per iteration, 100 times = ~5 GB if unbounded
            for (int i = 0; i < 100; i++) {
                byte[] block = new byte[50 * 1024 * 1024]; // 50 MB
                memoryLeakList.add(block);
            }
            return "Leaking memory rapidly...";
        } catch (OutOfMemoryError e) {
            return "OOM Error triggered!";
        }
    }
}
