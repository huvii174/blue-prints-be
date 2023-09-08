package com.bprints.be.transformer;

import com.bprints.be.dtos.PrintCategoryDto;
import com.bprints.be.dtos.PrintTagDto;
import com.bprints.be.entities.PrintCategory;

import java.util.HashSet;
import java.util.Set;

public class CategoryTransformer {
    public static PrintCategory toEntity(PrintCategoryDto printCategoryDto) {
        PrintCategory entity = new PrintCategory();
        entity.setName(printCategoryDto.getName());
        entity.setDescription(printCategoryDto.getDescription());

        return entity;
    }

    public static PrintCategoryDto toDtoWithPrintTags(PrintCategory printCategory) {
        PrintCategoryDto printCategoryDto = toDto(printCategory);

        //set print tags
        if (!printCategory.getTags().isEmpty()) {
            Set<PrintTagDto> printTagDtoSet = new HashSet<>();
            printCategory.getTags()
                    .forEach(printTag -> {
                        if (Boolean.TRUE.equals(printTag.getStatus())) {
                            printTagDtoSet.add(PrintTagTransformer.toDto(printTag));
                        }
                    });
            printCategoryDto.setTags(printTagDtoSet);
        }
        return printCategoryDto;
    }

    public static PrintCategoryDto toDto(PrintCategory printCategory) {
        return PrintCategoryDto.builder()
                .id(printCategory.getId())
                .name(printCategory.getName())
                .description(printCategory.getDescription())
                .status(printCategory.getStatus())
                .build();
    }
}
