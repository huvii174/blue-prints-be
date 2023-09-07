package com.bprints.be.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BluePrintDto {
    private Long id;

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String downloadLink;

    @NotNull
    private Integer fileSize;

    private Set<DesignToolDto> designTools;

    private Set<DesignStyleDto> designStyles;

    private Set<PrintTagDto> printTags;

    private Set<PrintCollectionDto> printCollections;

    private Set<Long> designToolIdList;

    private Set<Long> designStyleIdList;

    private Set<Long> printTagIdList;

    private Set<Long> printCollectionIdList;

    @NotNull
    private BigDecimal price;

    private Integer discount;

    @NotNull
    private Integer downloadCount;

    private Boolean status;
}
