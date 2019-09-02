package com.watson.annababy.model.aa;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(name="username", unique = true)
    String name;

    @Column(name="password")
    String passwd;

    @ManyToMany
    @JoinTable(name="user_role",
    joinColumns = {@JoinColumn(name="user_id")},
    inverseJoinColumns = {@JoinColumn(name="role_id")})
    List<RoleEntity> roles;
}
