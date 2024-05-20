package org.daewon.phreview.repository;

import jakarta.transaction.Transactional;
import org.daewon.phreview.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, String> {

    @Query("select u from Users u where u.email = :userId and u.social = false")
    Optional<Users> getWithRoles(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("update Users u set u.password = :password where u.email = :email")
    void updatePassword(@Param("password") String password, @Param("email") String email);

    Optional<Users> findByUserName(String username);
}