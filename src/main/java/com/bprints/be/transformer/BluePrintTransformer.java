package com.bprints.be.transformer;

import com.bprints.be.entities.BluePrint;
import com.bprints.be.dtos.BluePrintDto;

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

    public static BluePrintDto toBluePrintDto(BluePrint bluePrint){
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
