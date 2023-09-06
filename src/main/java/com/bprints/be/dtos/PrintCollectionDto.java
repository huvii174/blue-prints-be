package com.bprints.be.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
}
