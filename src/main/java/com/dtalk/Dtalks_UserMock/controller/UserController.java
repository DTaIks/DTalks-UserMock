package com.dtalk.Dtalks_UserMock.controller;

import com.dtalk.Dtalks_UserMock.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users.info")
    public ResponseEntity<?> getUserInfo(@RequestParam("user_id") String userId) {
        return userService.getUserInfo(userId)
                .map(user -> ResponseEntity.ok(Map.of(
                        "success", true,
                        "user", user
                )))
                .orElse(ResponseEntity.ok(Map.of(
                        "success", false,
                        "error", Map.of("code", "USER_NOT_FOUND")
                )));
    }
}
