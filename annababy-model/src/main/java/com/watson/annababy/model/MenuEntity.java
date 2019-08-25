package com.watson.annababy.model;

import com.watson.annababy.model.aa.ArtifactEntity;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name="text")
    String text;

    @OneToOne
    @JoinColumn(name="artifact_id")
    ArtifactEntity artifact;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="parent_id")
    MenuEntity parent;

    @OneToMany(mappedBy = "parent")
    List<MenuEntity> subMenuList;
}
