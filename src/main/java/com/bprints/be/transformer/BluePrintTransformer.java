package com.bprints.be.transformer;

import com.bprints.be.dtos.*;
import com.bprints.be.entities.BluePrint;

import java.util.HashSet;
import java.util.Set;

public class BluePrintTransformer {
    public static BluePrint toBluePrintEntity(BluePrintDto bluePrintDto) {
        BluePrint entity = new BluePrint();
        entity.setName(bluePrintDto.getName());
        entity.setDescription(bluePrintDto.getDescription());
        entity.setFileSize(bluePrintDto.getFileSize());
        entity.setDownloadLink(bluePrintDto.getDownloadLink());
        entity.setPrice(bluePrintDto.getPrice());
        entity.setDownloadCount(bluePrintDto.getDownloadCount());
        entity.setDiscount(bluePrintDto.getDiscount());
        return entity;
    }

    public static BluePrintDto toDtoWithOtherSets(BluePrint bluePrint) {
        BluePrintDto bluePrintDto = BluePrintDto.builder()
                .id(bluePrint.getId())
                .name(bluePrint.getName())
                .description(bluePrint.getDescription())
                .fileSize(bluePrint.getFileSize())
                .downloadCount(bluePrint.getDownloadCount())
                .downloadLink(bluePrint.getDownloadLink())
                .price(bluePrint.getPrice())
                .discount(bluePrint.getDiscount())
                .status(bluePrint.getStatus())
                .build();

        //Set Design style list
        Set<DesignStyleDto> designStyleDtoSet = new HashSet<>();
        bluePrint.getDesignStyles()
                .forEach(designStyle -> {
                    if (Boolean.TRUE.equals(designStyle.getStatus())) {
                        designStyleDtoSet.add(DesignStyleTransformer.toDto(designStyle));
                    }
                });
        bluePrintDto.setDesignStyles(designStyleDtoSet);

        //Set Design tool list
        Set<DesignToolDto> designToolDtoSet = new HashSet<>();
        bluePrint.getDesignTools()
                .forEach(designTool -> {
                    if (Boolean.TRUE.equals(designTool.getStatus())) {
                        designToolDtoSet.add(DesignToolTransformer.toDto(designTool));
                    }
                });
        bluePrintDto.setDesignTools(designToolDtoSet);

        //Set Design print tag
        Set<PrintTagDto> printTagDtoSet = new HashSet<>();
        bluePrint.getPrintTags()
                .forEach(printTag -> {
                    if (Boolean.TRUE.equals(printTag.getStatus())) {
                        printTagDtoSet.add(PrintTagTransformer.toDto(printTag));
                    }
                });
        bluePrintDto.setPrintTags(printTagDtoSet);

        //Set Design collection
        Set<PrintCollectionDto> collectionDtoSet = new HashSet<>();
        bluePrint.getPrintCollections()
                .forEach(collection -> {
                    if (Boolean.TRUE.equals(collection.getStatus())) {
                        collectionDtoSet.add(CollectionTransformer.toDto(collection));
                    }
                });
        bluePrintDto.setPrintCollections(collectionDtoSet);

        return bluePrintDto;
    }

    public static BluePrintDto toDto(BluePrint bluePrint) {

        return BluePrintDto.builder()
                .id(bluePrint.getId())
                .name(bluePrint.getName())
                .description(bluePrint.getDescription())
                .fileSize(bluePrint.getFileSize())
                .downloadCount(bluePrint.getDownloadCount())
                .downloadLink(bluePrint.getDownloadLink())
                .price(bluePrint.getPrice())
                .discount(bluePrint.getDiscount())
                .status(bluePrint.getStatus())
                .build();
    }
}
