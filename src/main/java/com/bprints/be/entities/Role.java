package com.bprints.be.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


@Entity
@Table(name = "role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 20)
    private ERole name;

    @OneToMany(mappedBy = "role")
    private List<User> users = new ArrayList<>();

    @Column(name = "created_by")
    private String createdBy = "System";

    @Column(name = "created_on")
    private Date createdOn = new Date();

    @Column(name = "updated_by")
    private String lastUpdatedBy = "System";

    @Column(name = "updated_on")
    private Date lastUpdatedOn = new Date();
}