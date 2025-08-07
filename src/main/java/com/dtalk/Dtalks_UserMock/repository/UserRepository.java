package com.dtalk.Dtalks_UserMock.repository;

import com.dtalk.Dtalks_UserMock.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    /**
     * 이메일(identifications.value) 기반으로 유저 조회
     * -> JOIN 없이 단순 필드에 있다면 다음처럼 추가
     */
    Optional<User> findByName(String name); // 예시

    // 나중에 필요 시 쿼리 확장 가능 (예: findByIdentifications_Value)
}

