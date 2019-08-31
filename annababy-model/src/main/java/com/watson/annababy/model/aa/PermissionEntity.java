package com.watson.annababy.model.aa;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="permission")
@Data
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(name="name")
    String name;
}
