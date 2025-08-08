package com.dtalk.Dtalks_UserMock.repository;

import com.dtalk.Dtalks_UserMock.entity.User;
import com.dtalk.Dtalks_UserMock.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    /**
     * 이메일(identifications.value) 기반으로 유저 조회
     * -> JOIN 없이 단순 필드에 있다면 다음처럼 추가
     */
    Optional<User> findById(Long id);

    @Query("SELECT u FROM User u JOIN FETCH u.identifications i WHERE i.type = 'email' AND i.value = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u JOIN FETCH u.identifications i " +
            "WHERE i.type = 'email' AND i.value = :email AND u.employeeNumber = :employeeNumber")
    Optional<User> findByEmailAndEmployeeNumber(@Param("email") String email,
                                                @Param("employeeNumber") String employeeNumber);

    @Query("SELECT u FROM User u " +
            "WHERE (:status IS NULL OR u.status = :status) " +
            "AND u.id > :cursorId " +
            "ORDER BY u.id ASC")
    List<User> findUserListWithCursorAndStatus(@Param("status") UserStatus status,
                                               @Param("cursorId") Long cursorId,
                                               Pageable pageable);

    boolean existsByEmployeeNumber(String employeeNumber);
}
