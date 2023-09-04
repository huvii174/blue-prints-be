package com.bprints.be.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "blue_print")
@Getter
@Setter
public class BluePrint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @NotBlank
    @Column(name = "download_link")
    private String downloadLink;

    @NotBlank
    @Column(name = "file_size")
    private Integer fileSize;

    @ManyToMany
    @JoinTable(
            name = "set_design_tool",
            joinColumns = @JoinColumn(name = "blue_print_id"),
            inverseJoinColumns = @JoinColumn(name = "design_tool_id"))
    private Set<DesignTool> designTools = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "set_design_style",
            joinColumns = @JoinColumn(name = "blue_print_id"),
            inverseJoinColumns = @JoinColumn(name = "design_style_id"))
    private Set<DesignStyle> designStyles = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "set_print_tag",
            joinColumns = @JoinColumn(name = "blue_print_id"),
            inverseJoinColumns = @JoinColumn(name = "print_tag_id"))
    private Set<PrintTag> printTags = new HashSet<>();

    @ManyToMany(mappedBy = "bluePrints")
    private Set<PrintCollection> printCollections = new HashSet<>();

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "discount")
    private Integer discount = 0;

    @NotNull
    @Column(name = "download_count")
    private Integer downloadCount = 0;

    @NotNull
    @Column(name = "status")
    private Boolean status = true;

    @Column(name = "created_by")
    private String createdBy = "System";

    @Column(name = "created_on")
    private Date createdOn = new Date();

    @Column(name = "updated_by")
    private String lastUpdatedBy = "System";

    @Column(name = "updated_on")
    private Date lastUpdatedOn = new Date();
}
