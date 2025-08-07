package com.dtalk.Dtalks_UserMock.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private ApiError error;

    public static ErrorResponse create(String code, String message) {
        return new ErrorResponse(ApiError.of(code, message));
    }
}
