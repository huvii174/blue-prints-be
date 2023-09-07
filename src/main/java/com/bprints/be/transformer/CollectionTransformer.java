package com.bprints.be.transformer;

import com.bprints.be.dtos.BluePrintDto;
import com.bprints.be.dtos.PrintCollectionDto;
import com.bprints.be.entities.BluePrint;
import com.bprints.be.entities.PrintCollection;

import java.util.Set;
import java.util.stream.Collectors;

public class CollectionTransformer {
    public static PrintCollection toEntity(PrintCollectionDto printCollectionDto){
        PrintCollection entity = new PrintCollection();
        entity.setName(printCollectionDto.getName());
        entity.setDescription(printCollectionDto.getDescription());
        entity.setDownloadCount(printCollectionDto.getDownloadCount());
        entity.setStatus(printCollectionDto.getStatus());

        return entity;
    }

    public static PrintCollectionDto toDtoWithBluePrints(PrintCollection printCollection){
        PrintCollectionDto printCollectionDto = toDto(printCollection);

        if (!printCollection.getBluePrints().isEmpty()){
            Set<BluePrintDto> bluePrintDtoSet = printCollection.getBluePrints().stream()
                    .map(bluePrint -> BluePrintTransformer.toBluePrintDto(bluePrint))
                    .collect(Collectors.toSet());
            printCollectionDto.setBluePrints(bluePrintDtoSet);
        }

        return printCollectionDto;
    }


    public static PrintCollectionDto toDto(PrintCollection printCollection){
        return PrintCollectionDto.builder()
                .id(printCollection.getId())
                .name(printCollection.getName())
                .description(printCollection.getDescription())
                .downloadCount(printCollection.getDownloadCount())
                .status(printCollection.getStatus())
                .build();
    }
}
