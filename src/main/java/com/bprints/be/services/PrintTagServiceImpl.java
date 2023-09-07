package com.bprints.be.services;

import com.bprints.be.dtos.PrintTagDto;
import com.bprints.be.entities.PrintCategory;
import com.bprints.be.entities.PrintTag;
import com.bprints.be.payload.response.PrintTagResponse;
import com.bprints.be.repositories.CategoryRepository;
import com.bprints.be.repositories.PrintTagRepository;
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
public class PrintTagServiceImpl implements PrintTagService {
    private PrintTagRepository printTagRepository;
    private CategoryRepository categoryRepository;

    @Override
    public void savePrintTag(PrintTagDto printTagDto) {
        log.info("PrintTagService :: savePrintTag request -> printTagDto {}", printTagDto);
        PrintTag printTag = PrintTagTransformer.toEntity(printTagDto);

        //Update case
        if (Objects.nonNull(printTagDto.getId())) {
            Long id = printTagDto.getId();
            Optional<PrintTag> optionalPrintTag = this.printTagRepository.findByIdAndStatus(id, true);
            if (optionalPrintTag.isEmpty()) {
                log.error("PrintTagService :: savePrintTag : Print tag Not Found with id: " + id);
            }
            printTag.setId(id);
        }

        //Add  category
        if (Objects.nonNull(printTagDto.getCategoryId())) {
            Long id = printTagDto.getCategoryId();
            Optional<PrintCategory> optionalCategory = this.categoryRepository.findByIdAndStatus(id, true);
            PrintCategory printCategory;
            if (optionalCategory.isEmpty()) {
                log.error("PrintTagService :: savePrintTag : Category Not Found with id: " + id);
                printCategory = null;
            } else printCategory = optionalCategory.get();

            printTag.setPrintCategory(printCategory);
        }

        this.printTagRepository.save(printTag);
    }

    @Override
    public PrintTagDto findById(Long id) {
        log.info("PrintTagService :: findById request id {}", id);
        Optional<PrintTag> optionalPrintTag = this.printTagRepository.findByIdAndStatus(id, true);
        if (optionalPrintTag.isEmpty()) {
            log.error("PrintTagService :: findById : Print tag Not Found with id: " + id);
            return null;
        }
        PrintTag printTag = optionalPrintTag.get();
        PrintTagDto printTagDto = PrintTagTransformer.toDtoWithCategory(printTag);

        return printTagDto;
    }

    @Override
    public PrintTagResponse findAll(Pageable pageable) {
        log.info("PrintTagService :: findAll");
        Page<PrintTag> page = this.printTagRepository.findByStatus(pageable, true);
        List<PrintTagDto> printTagDtoList = page.getContent().stream()
                .map(printTag -> PrintTagTransformer.toDtoWithCategory(printTag))
                .collect(Collectors.toList());

        PrintTagResponse printTagResponse = new PrintTagResponse();
        printTagResponse.setPrintTagDtoList(printTagDtoList);
        printTagResponse.setPageNo(page.getNumber());
        printTagResponse.setPageSize(page.getSize());
        printTagResponse.setTotalElements(page.getTotalElements());
        printTagResponse.setTotalPages(page.getTotalPages());
        return printTagResponse;
    }

    @Override
    public void changePrintTagStatus(List<Long> printTagIdList) {
        log.info("PrintTagService :: changePrintTagStatus request printTagIdList {}", Arrays.toString(printTagIdList.toArray()));
        List<PrintTag> printTags = new ArrayList<>();
        printTagIdList.stream()
                .forEach(id -> {
                    Optional<PrintTag> optionalPrintTag = this.printTagRepository.findByIdAndStatus(id, true);
                    if (optionalPrintTag.isPresent()) {
                        PrintTag printTag = optionalPrintTag.get();
                        printTag.setStatus(false);
                        printTag.setLastUpdatedOn(new Date());
                        printTags.add(printTag);
                    } else log.error("PrintTagService :: changeCategoryStatus : Print tag Not Found with id: " + id);
                });
        this.printTagRepository.saveAll(printTags);
    }
}
