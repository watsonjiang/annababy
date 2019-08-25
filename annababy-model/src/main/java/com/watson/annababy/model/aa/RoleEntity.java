package com.watson.annababy.model.aa;

import com.watson.annababy.model.UserEntity;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToMany(mappedBy = "role")
    List<PermissionEntity> permList;
}
