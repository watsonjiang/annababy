package com.watson.annababy.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 菜单表
 */
@Entity
@Table(name="menu")
@Data
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    /**
     * 菜单名,内部标识
     */
    @Column(name="name")
    String name;

    /**
     * 所属模块名，用于鉴权
     */
    @Column(name="module")
    String module;

    /**
     * 菜单打开窗口类型
     */
    @Column(name="classname")
    String className;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="parent_id")
    MenuEntity parent;

    @OneToMany(mappedBy = "parent")
    List<MenuEntity> subMenus;
}
