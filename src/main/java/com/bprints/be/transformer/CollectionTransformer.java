package com.bprints.be.transformer;

import com.bprints.be.dtos.PrintCollectionDto;
import com.bprints.be.entities.PrintCollection;

public class CollectionTransformer {
    public static PrintCollection toEntity(PrintCollectionDto printCollectionDto){
        PrintCollection entity = new PrintCollection();
        entity.setName(printCollectionDto.getName());
        entity.setDescription(printCollectionDto.getDescription());
        entity.setDownloadCount(printCollectionDto.getDownloadCount());
        entity.setStatus(printCollectionDto.getStatus());

        return entity;
    }

    public static PrintCollectionDto toDto(PrintCollection printCollection){
        return PrintCollectionDto.builder()
                .name(printCollection.getName())
                .description(printCollection.getDescription())
                .downloadCount(printCollection.getDownloadCount())
                .status(printCollection.getStatus())
                .build();
    }
}
