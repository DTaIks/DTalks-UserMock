package com.dtalk.Dtalks_UserMock.service;

import com.dtalk.Dtalks_UserMock.dto.UserResponse;
import com.dtalk.Dtalks_UserMock.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}

