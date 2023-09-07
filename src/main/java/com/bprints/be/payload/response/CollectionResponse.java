package com.bprints.be.payload.response;

import com.bprints.be.dtos.PrintCollectionDto;
import lombok.Data;

import java.util.List;

@Data
public class CollectionResponse extends ResponseBase{
    private List<PrintCollectionDto> collectionDtoList;
}
