package com.bprints.be.transformer;

import com.bprints.be.dtos.DesignToolDto;
import com.bprints.be.entities.DesignTool;

public class DesignToolTransformer {
    public static DesignTool toEntity(DesignToolDto designToolDto){
        DesignTool entity = new DesignTool();
        entity.setName(designToolDto.getName());

        return entity;
    }

    public static DesignToolDto toDto(DesignTool designTool){
        return DesignToolDto.builder()
                .name(designTool.getName())
                .build();
    }
}
