package com.dtalk.Dtalks_UserMock.dto;

import com.dtalk.Dtalks_UserMock.entity.Identification;
import com.dtalk.Dtalks_UserMock.entity.UserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String spaceId;

    @NotBlank
    private String name;

    private String nickname;
    private String avatarUrl;
    private String department;

    @NotBlank
    @Pattern(regexp = "\\d+", message = "사번은 숫자만 입력해야 합니다.")
    private String employeeNumber;

    private List<Identification> identifications;

    private String position;
    private String responsibility;
    private String tels;
    private String mobiles;
    private Long workStartTime;
    private Long workEndTime;
    private Long vacationStartTime;
    private Long vacationEndTime;

    @NotNull
    private UserStatus status;
}

