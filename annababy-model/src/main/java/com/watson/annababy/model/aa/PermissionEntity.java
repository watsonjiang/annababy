package com.watson.annababy.model.aa;

import com.watson.annababy.model.MenuEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="permission")
@Data
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToOne
    @JoinColumn(name="role_id")
    RoleEntity role;

    @OneToOne
    @JoinColumn(name = "artifact_id")
    ArtifactEntity artifact;

    // 0 - invisible 1 - readonly 2 - readwrite
    @Column(name="perm")
    String perm;
}
