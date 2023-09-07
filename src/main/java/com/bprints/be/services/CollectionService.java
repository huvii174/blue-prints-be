package com.bprints.be.services;

import com.bprints.be.dtos.PrintCollectionDto;
import com.bprints.be.payload.response.CollectionResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CollectionService {
    void saveCollection(PrintCollectionDto collectionDto);

    PrintCollectionDto findById(Long id);

    CollectionResponse findAll(Pageable pageable);

    void changeCollectionStatus(List<Long> collectionIdList);
}
