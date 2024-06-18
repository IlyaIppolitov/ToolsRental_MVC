package com.itexclusive.toolsrental_mvc.controllers;

import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.ProfileService;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.UserService;
import com.itexclusive.toolsrental_mvc.model.security.dto.UserDTO;
import com.itexclusive.toolsrental_mvc.model.security.dto.UserDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class RestAdminController {

    private final UserService userService;
    private final ProfileService profileService;

    @PostMapping("/update")
    public ResponseEntity<String> receiveUserData(@RequestBody UserDataDTO userData) {

        userService.update(userData);
        profileService.update(userData);
        return ResponseEntity.ok("Data received successfully");
    }
}
