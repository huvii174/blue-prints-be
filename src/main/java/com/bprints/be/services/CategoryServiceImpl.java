package com.bprints.be.services;

import com.bprints.be.dtos.PrintCategoryDto;
import com.bprints.be.entities.PrintCategory;
import com.bprints.be.payload.response.CategoryResponse;
import com.bprints.be.repositories.CategoryRepository;
import com.bprints.be.transformer.CategoryTransformer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public void saveCategory(PrintCategoryDto categoryDto) {
        log.info("CategoryService :: saveNewCategory request -> categoryDto {}", categoryDto);
        PrintCategory printCategory = CategoryTransformer.toEntity(categoryDto);

        //Update case
        if (Objects.nonNull(categoryDto.getId())) {
            Long id = categoryDto.getId();
            Optional<PrintCategory> optionalPrintCategory = this.categoryRepository.findByIdAndStatus(id, true);
            if (optionalPrintCategory.isEmpty()) {
                log.error("CategoryService :: saveNewCategory : Category Not Found with id: " + id);
            }
            else printCategory.setId(id);
        }

        this.categoryRepository.save(printCategory);
    }

    @Override
    public PrintCategoryDto findById(Long id) {
        log.info("CategoryService :: findById request id {}", id);
        Optional<PrintCategory> optionalCategory = this.categoryRepository.findByIdAndStatus(id, true);
        if (optionalCategory.isEmpty()) {
            log.error("CategoryService :: findById : Category Not Found with id: " + id);
            return null;
        }
        PrintCategory printCategory = optionalCategory.get();
        PrintCategoryDto printCategoryDto = CategoryTransformer.toDtoWithPrintTags(printCategory);

        return printCategoryDto;
    }

    @Override
    public CategoryResponse findAll(Pageable pageable) {
        log.info("CategoryService :: findAll");
        Page<PrintCategory> page = this.categoryRepository.findByStatus(pageable, true);
        List<PrintCategoryDto> printCategories = page.getContent().stream()
                .map(category -> CategoryTransformer.toDtoWithPrintTags(category))
                .collect(Collectors.toList());

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCategoryDtoList(printCategories);
        categoryResponse.setPageNo(page.getNumber());
        categoryResponse.setPageSize(page.getSize());
        categoryResponse.setTotalElements(page.getTotalElements());
        categoryResponse.setTotalPages(page.getTotalPages());
        return categoryResponse;
    }

    @Override
    public void changeCategoryStatus(List<Long> categoryIdList) {
        log.info("CategoryService :: changeCategoryStatus request categoryIdList {}", Arrays.toString(categoryIdList.toArray()));
        List<PrintCategory> printCategories = new ArrayList<>();
        categoryIdList.stream()
                .forEach(id -> {
                    Optional<PrintCategory> optionalPrintCategory = this.categoryRepository.findByIdAndStatus(id, true);
                    if (optionalPrintCategory.isPresent()) {
                        PrintCategory category = optionalPrintCategory.get();
                        category.setStatus(false);
                        category.setLastUpdatedOn(new Date());
                        printCategories.add(category);
                    } else log.error("CategoryService :: changeCategoryStatus : Category Not Found with id: " + id);
                });
        this.categoryRepository.saveAll(printCategories);
    }
}
