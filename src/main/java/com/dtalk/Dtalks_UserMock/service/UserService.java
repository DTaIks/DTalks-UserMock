package com.dtalk.Dtalks_UserMock.service;


import com.dtalk.Dtalks_UserMock.dto.UserListResponse;
import com.dtalk.Dtalks_UserMock.dto.UserResponse;
import com.dtalk.Dtalks_UserMock.entity.User;
import com.dtalk.Dtalks_UserMock.entity.UserStatus;
import com.dtalk.Dtalks_UserMock.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<UserResponse> getUserInfo(String userId) {
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

    public UserListResponse getUserList(int limit, String cursor, String statusIn) {
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


    private Long decodeCursorOrDefault(String cursor) {
        if (cursor == null) return 0L;
        try {
            return Long.parseLong(new String(Base64.getDecoder().decode(cursor)));
        } catch (Exception e) {
            return 0L;
        }
    }

}
