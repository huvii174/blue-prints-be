package com.bprints.be.transformer;

import com.bprints.be.dtos.PrintTagDto;
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
        if (Objects.nonNull(printTag.getPrintCategory())) {
            printTagDto.setPrintCategoryDto(CategoryTransformer.toDto(printTag.getPrintCategory()));
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
