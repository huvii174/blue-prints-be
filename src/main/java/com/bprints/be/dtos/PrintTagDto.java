package com.bprints.be.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrintTagDto {
    private Long id;

    private Long categoryId;

    @NotBlank
    private String name;

    private Boolean status;

    private PrintCategoryDto printCategoryDto;
}
