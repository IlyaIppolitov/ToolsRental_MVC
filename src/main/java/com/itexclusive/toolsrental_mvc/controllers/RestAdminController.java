package com.itexclusive.toolsrental_mvc.controllers;

import com.itexclusive.toolsrental_mvc.model.security.dto.UserDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class RestAdminController {

    @PostMapping("/your-server-endpoint")
    public ResponseEntity<String> receiveUserData(@RequestBody UserDataDTO userData) {
        System.out.println("Received data: " + userData);

        // Process the data here (e.g., save to database)

        return ResponseEntity.ok("Data received successfully");
    }
}
