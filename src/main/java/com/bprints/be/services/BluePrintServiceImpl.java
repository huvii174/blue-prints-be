package com.bprints.be.services;

import com.bprints.be.dtos.*;
import com.bprints.be.entities.*;
import com.bprints.be.payload.response.BluePrintResponse;
import com.bprints.be.repositories.*;
import com.bprints.be.transformer.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class BluePrintServiceImpl implements BluePrintService {
    private BluePrintRepository bluePrintRepository;
    private CollectionRepository collectionRepository;
    private DesignStyleRepository designStyleRepository;
    private DesignToolRepository designToolRepository;
    private PrintTagRepository printTagRepository;

    @Override
    public void saveBluePrint(BluePrintDto bluePrintDto) {
        log.info("BluePrintService :: saveBluePrint request -> bluePrintDto {}", bluePrintDto);
        BluePrint newBluePrint = BluePrintTransformer.toBluePrintEntity(bluePrintDto);

        //Add Design style list
        if (!bluePrintDto.getDesignStyleIdList().isEmpty()) {
            Set<DesignStyle> designStyleSet = new HashSet<>();
            bluePrintDto.getDesignStyleIdList().stream()
                    .forEach(id -> {
                        Optional<DesignStyle> optionalDesignStyle = this.designStyleRepository.findById(id);
                        if (optionalDesignStyle.isPresent()) {
                            designStyleSet.add(optionalDesignStyle.get());
                        } else log.info("BluePrintService :: saveBluePrint : Design style not exist for id: " + id);
                    });
            newBluePrint.setDesignStyles(designStyleSet);
        }

        //Add Design tool list
        if (!bluePrintDto.getDesignToolIdList().isEmpty()) {
            Set<DesignTool> designToolSet = new HashSet<>();
            bluePrintDto.getDesignToolIdList().stream()
                    .forEach(id -> {
                        Optional<DesignTool> optionalDesignTool = this.designToolRepository.findById(id);
                        if (optionalDesignTool.isPresent()) {
                            designToolSet.add(optionalDesignTool.get());
                        } else log.info("BluePrintService :: saveBluePrint : Design tool not exist for id: " + id);
                    });
            newBluePrint.setDesignTools(designToolSet);
        }

        //Add Design print tag
        if (!bluePrintDto.getPrintTagIdList().isEmpty()) {
            Set<PrintTag> printTagSet = new HashSet<>();
            bluePrintDto.getPrintTagIdList().stream()
                    .forEach(id -> {
                        Optional<PrintTag> optionalPrintTag = this.printTagRepository.findById(id);
                        if (optionalPrintTag.isPresent()) {
                            printTagSet.add(optionalPrintTag.get());
                        } else log.info("BluePrintService :: saveBluePrint : Print tag not exist for id: " + id);
                    });
            newBluePrint.setPrintTags(printTagSet);
        }

        //Add Design collection
        if (!bluePrintDto.getPrintCollectionIdList().isEmpty()) {
            Set<PrintCollection> printCollectionSet = new HashSet<>();
            bluePrintDto.getPrintCollectionIdList().stream()
                    .forEach(id -> {
                        Optional<PrintCollection> optionalPrintCollection = this.collectionRepository.findById(id);
                        if (optionalPrintCollection.isPresent()) {
                            printCollectionSet.add(optionalPrintCollection.get());
                        } else log.info("BluePrintService :: saveBluePrint : Print collection not exist for id: " + id);
                    });
            newBluePrint.setPrintCollections(printCollectionSet);
        }

        this.bluePrintRepository.save(newBluePrint);
    }

    @Override
    public BluePrintDto getBluePrintById(Long id) {
        log.info("BluePrintService :: getBluePrintById request id {}", id);
        BluePrint bluePrint = this.bluePrintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BluePrintService :: getBluePrintById : Blue print Not Found with id: " + id));
        BluePrintDto bluePrintDto = BluePrintTransformer.toBluePrintDto(bluePrint);

        //Set Design style list
        Set<DesignStyleDto> designStyleDtoSet = bluePrint.getDesignStyles().stream()
                .map(designStyle -> DesignStyleTransformer.toDto(designStyle))
                .collect(Collectors.toSet());
        bluePrintDto.setDesignStyles(designStyleDtoSet);

        //Set Design tool list
        Set<DesignToolDto> designToolDtoSet = bluePrint.getDesignTools().stream()
                .map(designTool -> DesignToolTransformer.toDto(designTool))
                .collect(Collectors.toSet());
        bluePrintDto.setDesignTools(designToolDtoSet);

        //Set Design print tag
        Set<PrintTagDto> printTagDtoSet = bluePrint.getPrintTags().stream()
                .map(printTag -> PrintTagTransformer.toDto(printTag))
                .collect(Collectors.toSet());
        bluePrintDto.setPrintTags(printTagDtoSet);

        //Set Design collection
        Set<PrintCollectionDto> collectionDtoSet = bluePrint.getPrintCollections().stream()
                .map(collection -> CollectionTransformer.toDto(collection))
                .collect(Collectors.toSet());
        bluePrintDto.setPrintCollections(collectionDtoSet);

        return bluePrintDto;
    }

    @Override
    public BluePrintResponse searchBluePrint(Pageable pageable, String name, Long tagId, Long toolId, Long styleId) {
        Specification<BluePrint> spec = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null) {
                predicates.add(builder.like(root.get("name"), name));
            }

            if (tagId != null) {
                Join<BluePrint, Class> classJoin = root.join("printTags");
                predicates.add(builder.equal(classJoin.get("id"), tagId));
            }

            if (toolId != null) {
                Join<BluePrint, Class> classJoin = root.join("designTools");
                predicates.add(builder.equal(classJoin.get("id"), tagId));
            }

            if (styleId != null) {
                Join<BluePrint, Class> classJoin = root.join("designStyles");
                predicates.add(builder.equal(classJoin.get("id"), tagId));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        Page<BluePrint> page = this.bluePrintRepository.findAll(spec, pageable);
        List<BluePrintDto> bluePrintDtoList = page.getContent().stream()
                .map(bluePrint -> BluePrintTransformer.toBluePrintDto(bluePrint))
                .collect(Collectors.toList());
        return new BluePrintResponse(bluePrintDtoList,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages());
    }
//

    @Override
    public void changeBluePrintStatus(List<Long> bluePrintIdList) {
        log.info("BluePrintService :: changeBluePrintStatus request bluePrintIdList {}", Arrays.toString(bluePrintIdList.toArray()));
        List<BluePrint> bluePrints = new ArrayList<>();
        bluePrintIdList.stream()
                .forEach(id -> {
                    Optional<BluePrint> optionalBluePrint = this.bluePrintRepository.findById(id);
                    if (optionalBluePrint.isPresent()) {
                        BluePrint bluePrint = optionalBluePrint.get();
                        bluePrint.setStatus(false);
                        bluePrint.setLastUpdatedOn(new Date());
                        bluePrints.add(bluePrint);
                    } else log.error("BluePrintService :: getBluePrintById : Blue print Not Found with id: " + id);
                });
        this.bluePrintRepository.saveAll(bluePrints);
    }
}
