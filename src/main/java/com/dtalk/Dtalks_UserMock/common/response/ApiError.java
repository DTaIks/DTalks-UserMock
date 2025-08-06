package com.dtalk.Dtalks_UserMock.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiError {
    private String code;
    private String message;

    public static ApiError of(String code, String message) {
        return ApiError.builder()
                .code(code)
                .message(message)
                .build();
    }
}
