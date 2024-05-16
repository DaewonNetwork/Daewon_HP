package org.daewon.phreview.repository;

import org.daewon.phreview.domain.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    @EntityGraph(attributePaths = "roleSet")
    @Query("select u from Users u where u.email = :email")
    Optional<Users> getWithRoles(String email);
}
