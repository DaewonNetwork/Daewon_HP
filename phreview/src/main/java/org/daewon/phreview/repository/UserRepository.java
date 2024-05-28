package org.daewon.phreview.repository;

import jakarta.transaction.Transactional;
import org.daewon.phreview.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    @Query("select u from Users u where u.userId = :userId and u.social = false")
    Optional<Users> getWithRoles(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query("update Users u set u.password = :password where u.userId = :userId")
    void updatePassword(@Param("password") String password, @Param("userId") String mid);

    Optional<Users> findByUserName(String userName);

    boolean existsByEmail(String email);

    Optional<Users> findByEmail(String email);

    Optional<Users> findByEmailAndPassword(String users_email, String password);
}