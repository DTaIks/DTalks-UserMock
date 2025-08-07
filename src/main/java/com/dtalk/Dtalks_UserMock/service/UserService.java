package com.dtalk.Dtalks_UserMock.service;


import com.dtalk.Dtalks_UserMock.common.exception.custom.ConflictException;
import com.dtalk.Dtalks_UserMock.common.exception.custom.InternalServerException;
import com.dtalk.Dtalks_UserMock.common.exception.custom.NotFoundException;
import com.dtalk.Dtalks_UserMock.dto.UserListResponse;
import com.dtalk.Dtalks_UserMock.dto.UserRequest;
import com.dtalk.Dtalks_UserMock.dto.UserResponse;
import com.dtalk.Dtalks_UserMock.entity.User;
import com.dtalk.Dtalks_UserMock.entity.UserStatus;
import com.dtalk.Dtalks_UserMock.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<UserResponse> getUserInfo(Long userId) {
        return userRepository.findById(userId)
                .map(UserResponse::from);
    }
    
    public Optional<UserResponse> getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserResponse::from);
    }

    public Optional<UserResponse> getUserByEmailAndEmployeeNumber(String email, String employeeNumber) {
        return userRepository.findByEmailAndEmployeeNumber(email, employeeNumber)
                .map(UserResponse::from);
    }

    public UserListResponse getUserList(int limit, Long cursor, String statusIn) {
        Long cursorId = decodeCursorOrDefault(cursor);

        // 안전한 enum 변환 (대소문자 무시)
        UserStatus userStatus = null;
        if (statusIn != null) {
            userStatus = Arrays.stream(UserStatus.values())
                    .filter(s -> s.name().equalsIgnoreCase(statusIn))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid status: " + statusIn));
        }

        // 캐스팅 제거
        List<User> users = userRepository.findUserListWithCursorAndStatus(
                userStatus,
                cursorId,
                PageRequest.of(0, limit + 1, Sort.by(Sort.Direction.ASC, "id"))
        );

        boolean hasNext = users.size() > limit;
        if (hasNext) users = users.subList(0, limit);

        String nextCursor = hasNext
                ? Base64.getEncoder().encodeToString(users.get(users.size() - 1).getId().toString().getBytes())
                : null;

        List<UserResponse> userResponses = users.stream()
                .map(UserResponse::from)
                .toList();

        return new UserListResponse(true, nextCursor, userResponses);
    }

    private Long decodeCursorOrDefault(Long cursor) {
        return cursor != null ? cursor : 0L; // 기본값 처리
    }

    @Transactional
    public void addUser(UserRequest request) {
        // 1. 사번 중복 체크
        if (userRepository.existsByEmployeeNumber(request.getEmployeeNumber())) {
            throw new ConflictException("이미 존재하는 사번입니다.");
        }

        // 2. 사용자 생성
        String userId = UUID.randomUUID().toString();

        User user = User.builder()
//                .id(userId)  // 자동 생성됨
                .spaceId(request.getSpaceId())
                .name(request.getName())
                .nickname(request.getNickname())
                .avatarUrl(request.getAvatarUrl())
                .department(request.getDepartment())
                .employeeNumber(request.getEmployeeNumber())
                .position(request.getPosition())
                .responsibility(request.getResponsibility())
                .tels(request.getTels())
                .mobiles(request.getMobiles())
                .workStartTime(request.getWorkStartTime())
                .workEndTime(request.getWorkEndTime())
                .vacationStartTime(request.getVacationStartTime())
                .vacationEndTime(request.getVacationEndTime())
                .status(request.getStatus())
                .identifications(request.getIdentifications())
                .build();

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new InternalServerException("사용자 저장 중 오류가 발생했습니다.");
        }
    }

    @Transactional
    public UserResponse updateUser(Long userId, UserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("유저를 찾을 수 없습니다."));

        user.updateFromRequest(request);
        return UserResponse.from(user);
    }


}
