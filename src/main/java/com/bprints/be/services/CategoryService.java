package com.bprints.be.services;

import com.bprints.be.dtos.PrintCategoryDto;
import com.bprints.be.payload.response.CategoryResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    void saveCategory(PrintCategoryDto categoryDto);
    PrintCategoryDto findById(Long id);
    CategoryResponse findAll(Pageable pageable);
    void changeCategoryStatus(List<Long> categoryIdList);
}
