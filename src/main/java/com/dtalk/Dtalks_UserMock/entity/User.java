package com.dtalk.Dtalks_UserMock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private String id;

    private String spaceId;
    private String name;
    private String nickname;
    private String avatarUrl;
    private String department;

    private String employeeNumber; //사번 추가

    @ElementCollection
    @CollectionTable(
            name = "user_identifications",
            joinColumns = @JoinColumn(name = "user_id")
    )
    private List<Identification> identifications;

    private String position;
    private String responsibility;
    private String tels;
    private String mobiles;

    private Long workStartTime;
    private Long workEndTime;
    private Long vacationStartTime;
    private Long vacationEndTime;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
}

