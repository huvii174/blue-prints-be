package com.bprints.be.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Table(name = "category")
@Getter
@Setter
public class PrintCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "printCategory")
    private Set<PrintTag> tags = new HashSet<>();

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "description")
    private String description;

    @Column(name = "created_by")
    private String createdBy = "System";

    @Column(name = "created_on")
    private Date createdOn = new Date();

    @Column(name = "updated_by")
    private String lastUpdatedBy = "System";

    @Column(name = "updated_on")
    private Date lastUpdatedOn = new Date();
}
