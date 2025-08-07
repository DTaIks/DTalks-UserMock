package com.dtalk.Dtalks_UserMock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListResponse {
    private boolean success;
    private String cursor;
    private List<UserResponse> users;
}
