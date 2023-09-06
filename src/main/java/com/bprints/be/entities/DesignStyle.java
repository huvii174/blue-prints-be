package com.bprints.be.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "design_style")
@Getter
@Setter
public class DesignStyle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "status")
    private Boolean status = true;

    @ManyToMany(mappedBy = "designStyles")
    private Set<BluePrint> blueprints = new HashSet<>();

    @Column(name = "created_by")
    private String createdBy = "System";

    @Column(name = "created_on")
    private Date createdOn = new Date();

    @Column(name = "updated_by")
    private String lastUpdatedBy = "System";

    @Column(name = "updated_on")
    private Date lastUpdatedOn = new Date();
}
