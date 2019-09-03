package com.watson.annababy.model.aa;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 角色表
 * 一个角色对应n个权限
 * 一个权限对应一个授权实体
 */
@Entity
@Table(name="role")
@Data
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(unique = true)
    String name;

    @OneToMany
    @JoinTable(name="role_permission",
            joinColumns = {@JoinColumn(name="role_id")},
            inverseJoinColumns = {@JoinColumn(name="permission_id")})
    List<PermissionEntity> permissions;

    @ManyToMany(mappedBy = "roles")
    List<UserEntity> users;
}
