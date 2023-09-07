package com.bprints.be.transformer;

import com.bprints.be.dtos.DesignStyleDto;
import com.bprints.be.entities.DesignStyle;

public class DesignStyleTransformer {
    public static DesignStyle toEntity(DesignStyleDto designStyleDto){
        DesignStyle entity = new DesignStyle();
        entity.setName(designStyleDto.getName());

        return entity;
    }

    public static DesignStyleDto toDto(DesignStyle designStyle){
        return DesignStyleDto.builder()
                .id(designStyle.getId())
                .name(designStyle.getName())
                .status(designStyle.getStatus())
                .build();
    }
}
