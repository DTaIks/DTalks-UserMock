package com.dtalk.Dtalks_UserMock.dto;

import com.dtalk.Dtalks_UserMock.entity.Identification;
import com.dtalk.Dtalks_UserMock.entity.RoleType;
import com.dtalk.Dtalks_UserMock.entity.UserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

    String spaceId;

    @NotBlank
    String name;

    String nickname;
    String avatarUrl;
    String department;

    @NotBlank
    @Pattern(regexp = "\\d+", message = "사번은 숫자만 입력해야 합니다.")
    String employeeNumber;

    List<Identification> identifications;

    String position;
    String responsibility;
    String tels;
    String mobiles;

    Long workStartTime;
    Long workEndTime;
    Long vacationStartTime;
    Long vacationEndTime;

    @NotNull
    UserStatus status;

    @NotNull
    RoleType role; //  User와 일치시키려면 포함하는 게 좋음
}
