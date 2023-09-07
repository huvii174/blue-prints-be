package com.bprints.be.dtos;

import com.bprints.be.entities.BluePrint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrintCollectionDto {
    private Long id;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private Integer downloadCount;

    private Boolean status;

    private Set<Long> bluePrintIdList;
    private Set<BluePrintDto> bluePrints;
}
