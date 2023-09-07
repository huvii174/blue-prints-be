package com.bprints.be.services;

import com.bprints.be.dtos.DesignStyleDto;
import com.bprints.be.dtos.PrintCollectionDto;
import com.bprints.be.payload.response.CollectionResponse;
import com.bprints.be.payload.response.DesignStyleResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DesignStyleService {
    void saveStyle(DesignStyleDto designStyleDto);

    DesignStyleDto findById(Long id);

    DesignStyleResponse findAll(Pageable pageable);

    void changeStyleStatus(List<Long> styleIdList);
}
