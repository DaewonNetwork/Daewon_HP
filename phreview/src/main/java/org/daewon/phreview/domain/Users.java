package org.daewon.phreview.domain;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roleSet")
public class Users extends BaseEntity{

    @Id
    private Long userId;

    private String password;
    private String userName;
    private String email;
    private boolean social;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<UserRole> roleSet = new HashSet<>();

    public void setPassword(String password) {
        this.password = password;
    }

    public void addRole(UserRole userRole) {
        this.roleSet.add(userRole);
    }
}
