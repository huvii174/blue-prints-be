package com.bprints.be.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrintCategoryDto {
    private Long id;
    private Set<PrintTagDto> tags;
    private Set<Long> tagIdList;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private Boolean status;
}
