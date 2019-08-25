package com.watson.annababy.model;

import com.watson.annababy.model.aa.RoleEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name="username", unique = true)
    String name;

    @Column(name="password")
    String passwd;

    @OneToOne
    @JoinColumn(name = "role_id")
    RoleEntity role;
}
