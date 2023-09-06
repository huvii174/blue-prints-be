package com.bprints.be.services;

import com.bprints.be.dtos.PrintCategoryDto;
import com.bprints.be.dtos.PrintTagDto;
import com.bprints.be.entities.PrintCategory;
import com.bprints.be.entities.PrintTag;
import com.bprints.be.payload.response.CategoryResponse;
import com.bprints.be.repositories.CategoryRepository;
import com.bprints.be.repositories.PrintTagRepository;
import com.bprints.be.transformer.CategoryTransformer;
import com.bprints.be.transformer.PrintTagTransformer;
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
    private PrintTagRepository printTagRepository;

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
            printCategory.setId(id);
        }

//        //Add  print tag list
//        if (!categoryDto.getTagIdList().isEmpty()) {
//            Set<PrintTag> printTagSet = new HashSet<>();
//            categoryDto.getTagIdList().stream()
//                    .forEach(id -> {
//                        Optional<PrintTag> optionalPrintTag = this.printTagRepository.findById(id);
//                        if (optionalPrintTag.isPresent()) {
//                            printTagSet.add(optionalPrintTag.get());
//                        } else log.info("CategoryService :: saveNewCategory : Print tag not exist for id: " + id);
//                    });
//            printCategory.setTags(printTagSet);
//        }

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
        PrintCategoryDto printCategoryDto = CategoryTransformer.toDto(printCategory);

        //Set Design print tag
        Set<PrintTagDto> printTagDtoSet = printCategory.getTags().stream()
                .map(printTag -> PrintTagTransformer.toDto(printTag))
                .collect(Collectors.toSet());
        printCategoryDto.setTags(printTagDtoSet);

        return printCategoryDto;
    }

    @Override
    public CategoryResponse findAll(Pageable pageable) {
        log.info("CategoryService :: findAll");
        Page<PrintCategory> page = this.categoryRepository.findByStatus(pageable, true);
        List<PrintCategoryDto> printCategories = page.getContent().stream()
                .map(category -> CategoryTransformer.toDto(category))
                .collect(Collectors.toList());
        return new CategoryResponse(printCategories,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages());
    }

    @Override
    public void changeCategoryStatus(List<Long> categoryIdList) {
        log.info("CategoryService :: changeCategoryStatus request bluePrintIdList {}", Arrays.toString(categoryIdList.toArray()));
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
