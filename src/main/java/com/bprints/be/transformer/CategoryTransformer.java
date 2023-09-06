package com.bprints.be.transformer;

import com.bprints.be.dtos.PrintCategoryDto;
import com.bprints.be.entities.PrintCategory;

public class CategoryTransformer {
    public static PrintCategory toEntity(PrintCategoryDto printCategoryDto){
        PrintCategory entity = new PrintCategory();
        entity.setName(printCategoryDto.getName());
        entity.setDescription(printCategoryDto.getDescription());

        return entity;
    }

    public static PrintCategoryDto toDto(PrintCategory printCategory){
        return PrintCategoryDto.builder()
                .id(printCategory.getId())
                .name(printCategory.getName())
                .description(printCategory.getDescription())
                .status(printCategory.getStatus())
                .build();
    }
}
