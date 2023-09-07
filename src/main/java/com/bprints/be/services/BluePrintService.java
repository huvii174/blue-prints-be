package com.bprints.be.services;

import com.bprints.be.dtos.BluePrintDto;
import com.bprints.be.payload.response.BluePrintResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BluePrintService {
    void saveBluePrint(BluePrintDto bluePrintDto);
    BluePrintDto getBluePrintById(Long id);
    BluePrintResponse searchBluePrint(Pageable pageable, String name, Long tagId, Long toolId, Long styleId);
    void changeBluePrintStatus(List<Long> bluePrintIdList);

}
