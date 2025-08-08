package com.dtalk.Dtalks_UserMock.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum UserStatus {
    ACTIVATED,
    DEACTIVATED,
    INOPERATIVE;

    @JsonCreator
    public static UserStatus from(String value) {
        return Arrays.stream(UserStatus.values())
                .filter(status -> status.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid UserStatus: " + value));
    }
}
