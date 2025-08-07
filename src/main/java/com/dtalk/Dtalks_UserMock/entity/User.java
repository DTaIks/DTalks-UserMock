package com.dtalk.Dtalks_UserMock.entity;

import com.dtalk.Dtalks_UserMock.dto.UserRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public void updateFromRequest(UserRequest request) {
        this.name = request.getName();
        this.nickname = request.getNickname();
        this.avatarUrl = request.getAvatarUrl();
        this.department = request.getDepartment();
        this.position = request.getPosition();
        this.responsibility = request.getResponsibility();
        this.tels = request.getTels();
        this.mobiles = request.getMobiles();
        this.workStartTime = request.getWorkStartTime();
        this.workEndTime = request.getWorkEndTime();
        this.vacationStartTime = request.getVacationStartTime();
        this.vacationEndTime = request.getVacationEndTime();
        this.status = request.getStatus();

        // ID 정보는 먼저 삭제하고 다시 등록
        this.identifications.clear();
        if (request.getIdentifications() != null) {
            this.identifications.addAll(request.getIdentifications());
        }
    }

}
