package com.dtalk.Dtalks_UserMock.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum RoleType {
    MASTER("관리자", "Administrator"),
    ADMIN("편집자", "Editor"),
    USER("사용자", "User");

    private String name;
    private String nameEn;
}

