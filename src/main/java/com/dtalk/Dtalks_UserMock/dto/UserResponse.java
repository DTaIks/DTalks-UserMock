package com.dtalk.Dtalks_UserMock.dto;

import com.dtalk.Dtalks_UserMock.entity.Identification;
import com.dtalk.Dtalks_UserMock.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String spaceId;
    private String name;
    private String nickname;
    private String avatarUrl;
    private String department;
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
    private String status;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .spaceId(user.getSpaceId())
                .name(user.getName())
                .nickname(user.getNickname())
                .avatarUrl(user.getAvatarUrl())
                .department(user.getDepartment())
                .employeeNumber(user.getEmployeeNumber())
                .identifications(user.getIdentifications())
                .position(user.getPosition())
                .responsibility(user.getResponsibility())
                .tels(user.getTels())
                .mobiles(user.getMobiles())
                .workStartTime(user.getWorkStartTime())
                .workEndTime(user.getWorkEndTime())
                .vacationStartTime(user.getVacationStartTime())
                .vacationEndTime(user.getVacationEndTime())
                .status(user.getStatus().name())
                .build();
    }
}
