package com.watson.annababy.model.aa;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 授权实体表
 */
@Entity
@Table(name="artifact")
@Data
public class ArtifactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToMany(mappedBy = "artifact")
    List<PermissionEntity> permList;

}
