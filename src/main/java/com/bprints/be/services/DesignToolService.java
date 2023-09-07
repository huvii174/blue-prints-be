package com.bprints.be.services;

import com.bprints.be.dtos.DesignToolDto;
import com.bprints.be.payload.response.DesignToolResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DesignToolService {
    void saveTool(DesignToolDto designToolDto);

    DesignToolDto findById(Long id);

    DesignToolResponse findAll(Pageable pageable);

    void changeToolStatus(List<Long> toolIdList);
}
