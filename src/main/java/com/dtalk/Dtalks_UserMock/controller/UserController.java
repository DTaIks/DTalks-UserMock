package com.dtalk.Dtalks_UserMock.controller;

import com.dtalk.Dtalks_UserMock.common.response.ApiError;
import com.dtalk.Dtalks_UserMock.common.response.ApiResponse;
import com.dtalk.Dtalks_UserMock.dto.UserListResponse;
import com.dtalk.Dtalks_UserMock.dto.UserRequest;
import com.dtalk.Dtalks_UserMock.dto.UserResponse;
import com.dtalk.Dtalks_UserMock.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/info")
    public ResponseEntity<ApiResponse<UserResponse>> getUserInfo(@RequestParam("user_id") Long userId) {
        return userService.getUserInfo(userId)
                .map(user -> ResponseEntity.ok(ApiResponse.success(user)))
                .orElse(ResponseEntity.ok(ApiResponse.<UserResponse>fail(
                        ApiError.of("user_not_found", "멤버를 찾을 수 없음")
                )));
    }

    @GetMapping("/users/find_by_email")
    public ResponseEntity<ApiResponse<UserResponse>> getUserByEmail(@RequestParam("email") String email) {
        return userService.getUserByEmail(email)
                .map(user -> ResponseEntity.ok(ApiResponse.success(user)))
                .orElse(ResponseEntity.ok(ApiResponse.fail(
                        ApiError.of("user_not_found", "멤버를 찾을 수 없음")
                )));
    }

    @GetMapping("/users/find_by_email_and_employee_number")
    public ResponseEntity<ApiResponse<UserResponse>> getUserByEmailAndEmployeeNumber(
            @RequestParam("email") String email,
            @RequestParam("employee_number") String employeeNumber) {

        return userService.getUserByEmailAndEmployeeNumber(email, employeeNumber)
                .map(user -> ResponseEntity.ok(ApiResponse.success(user)))
                .orElse(ResponseEntity.ok(ApiResponse.fail(
                        ApiError.of("user_not_found", "멤버를 찾을 수 없음")
                )));
    }

    @GetMapping("/users/list")
    public ResponseEntity<UserListResponse> getUserList(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) Long cursor, // ← 수정
            @RequestParam(name = "status_in", required = false) String statusIn
    ) {
        return ResponseEntity.ok(userService.getUserList(limit, cursor, statusIn));
    }

    @PostMapping("/users")
    public ResponseEntity<Void> addUser(@Valid @RequestBody UserRequest request) {
        userService.addUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
