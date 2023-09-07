package com.bprints.be.services;

import com.bprints.be.dtos.PrintCategoryDto;
import com.bprints.be.dtos.PrintTagDto;
import com.bprints.be.entities.PrintTag;
import com.bprints.be.payload.response.CategoryResponse;
import com.bprints.be.payload.response.PrintTagResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PrintTagService {
    void savePrintTag(PrintTagDto printTagDto);
    PrintTagDto findById(Long id);
    PrintTagResponse findAll(Pageable pageable);
    void changePrintTagStatus(List<Long> printTagIdList);
}
