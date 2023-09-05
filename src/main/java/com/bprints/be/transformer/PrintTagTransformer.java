package com.bprints.be.transformer;

import com.bprints.be.dtos.PrintTagDto;
import com.bprints.be.entities.PrintTag;

public class PrintTagTransformer {
    public static PrintTag toEntity(PrintTagDto printTagDto){
        PrintTag entity = new PrintTag();
        entity.setName(printTagDto.getName());

        return entity;
    }

    public static PrintTagDto toDto(PrintTag printTag){
        return PrintTagDto.builder()
                .name(printTag.getName())
                .build();
    }
}
