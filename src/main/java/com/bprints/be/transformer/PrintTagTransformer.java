package com.bprints.be.transformer;

import com.bprints.be.dtos.PrintTagDto;
import com.bprints.be.entities.PrintCategory;
import com.bprints.be.entities.PrintTag;

import java.util.Objects;

public class PrintTagTransformer {
    public static PrintTag toEntity(PrintTagDto printTagDto) {
        PrintTag entity = new PrintTag();
        entity.setName(printTagDto.getName());

        return entity;
    }

    public static PrintTagDto toDtoWithCategory(PrintTag printTag) {
        PrintTagDto printTagDto = toDto(printTag);

        //Set Category
        PrintCategory printCategory = printTag.getPrintCategory();
        if (Objects.nonNull(printCategory) && Boolean.TRUE.equals(printCategory.getStatus())) {
            printTagDto.setPrintCategoryDto(CategoryTransformer.toDto(printCategory));
        }
        return printTagDto;
    }

    public static PrintTagDto toDto(PrintTag printTag) {
        return PrintTagDto.builder()
                .id(printTag.getId())
                .name(printTag.getName())
                .status(printTag.getStatus())
                .build();
    }
}
