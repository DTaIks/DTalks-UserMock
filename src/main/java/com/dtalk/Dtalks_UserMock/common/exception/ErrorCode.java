package com.dtalk.Dtalks_UserMock.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_PARAMETER("invalid_parameter", "제공된 파라미터의 값이 올바르지 않음"),
    INVALID_AUTHENTICATION("invalid_authentication", "제공된 인증키가 유효하지 않음"),
    API_NOT_FOUND("api_not_found", "요청 API의 URL 혹은 HTTP 메서드와 다름"),
    UNAUTHORIZED("unauthorized", "인증키가 제공되지 않음"),
    INTERNAL_SERVER_ERROR("internal_server_error", "정의되지 않은 서버 오류 발생"),
    EXPIRED_AUTHENTICATION("expired_authentication", "제공된 인증키가 만료됨"),
    INVALID_CONTENT_TYPE("invalid_content_type", "요청이 API가 요구하는 Content Type과 다름"),
    MISSING_PARAMETER("missing_parameter", "필요한 파라미터 값이 제공되지 않았음"),
    USER_NOT_FOUND("user_not_found", "멤버를 찾을 수 없음");

    private final String code;
    private final String message;
}
