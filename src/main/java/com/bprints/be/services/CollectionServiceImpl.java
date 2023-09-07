package com.bprints.be.services;

import com.bprints.be.dtos.PrintCollectionDto;
import com.bprints.be.entities.BluePrint;
import com.bprints.be.entities.PrintCollection;
import com.bprints.be.payload.response.CollectionResponse;
import com.bprints.be.repositories.BluePrintRepository;
import com.bprints.be.repositories.CollectionRepository;
import com.bprints.be.transformer.CollectionTransformer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CollectionServiceImpl implements CollectionService {
    private CollectionRepository collectionRepository;
    private BluePrintRepository bluePrintRepository;

    @Override
    public void saveCollection(PrintCollectionDto collectionDto) {
        log.info("CollectionService :: saveCollection request -> collectionDto {}", collectionDto);
        PrintCollection collection = CollectionTransformer.toEntity(collectionDto);

        //Update case
        if (Objects.nonNull(collectionDto.getId())) {
            Long id = collectionDto.getId();
            Optional<PrintCollection> optionalPrintCollection = this.collectionRepository.findByIdAndStatus(id, true);
            if (optionalPrintCollection.isEmpty()) {
                log.error("CollectionService :: saveCollection : Print collection Not Found with id: " + id);
            }
            else collection.setId(id);
        }

        //Add  blueprints
        if (!collectionDto.getBluePrintIdList().isEmpty()) {
            Set<BluePrint> bluePrintSet = new HashSet<>();
            collectionDto.getBluePrintIdList().stream()
                    .forEach(id -> {
                        Optional<BluePrint> optionalBluePrint = this.bluePrintRepository.findByIdAndStatus(id, true);
                        if (optionalBluePrint.isPresent()) {
                            bluePrintSet.add(optionalBluePrint.get());
                        } else log.info("CollectionService :: saveCollection : Blue print not exist for id: " + id);
                    });
            collection.setBluePrints(bluePrintSet);
        }

        this.collectionRepository.save(collection);
    }

    @Override
    public PrintCollectionDto findById(Long id) {
        log.info("CollectionService :: findById request id {}", id);
        Optional<PrintCollection> optionalPrintCollection = this.collectionRepository.findByIdAndStatus(id, true);
        if (optionalPrintCollection.isEmpty()) {
            log.error("CollectionService :: findById : Collection Not Found with id: " + id);
            return null;
        }
        PrintCollection collection = optionalPrintCollection.get();
        PrintCollectionDto printCollectionDto = CollectionTransformer.toDtoWithBluePrints(collection);

        return printCollectionDto;
    }

    @Override
    public CollectionResponse findAll(Pageable pageable) {
        log.info("CollectionService :: findAll");
        Page<PrintCollection> page = this.collectionRepository.findByStatus(pageable, true);
        List<PrintCollectionDto> printCollectionDtoList = page.getContent().stream()
                .map(collection -> CollectionTransformer.toDtoWithBluePrints(collection))
                .collect(Collectors.toList());

        CollectionResponse collectionResponse = new CollectionResponse();
        collectionResponse.setCollectionDtoList(printCollectionDtoList);
        collectionResponse.setPageNo(page.getNumber());
        collectionResponse.setPageSize(page.getSize());
        collectionResponse.setTotalElements(page.getTotalElements());
        collectionResponse.setTotalPages(page.getTotalPages());
        return collectionResponse;
    }

    @Override
    public void changeCollectionStatus(List<Long> collectionIdList) {
        log.info("CollectionService :: changeCollectionStatus request collectionIdList {}", Arrays.toString(collectionIdList.toArray()));
        List<PrintCollection> collections = new ArrayList<>();
        collectionIdList.stream()
                .forEach(id -> {
                    Optional<PrintCollection> optionalPrintCollection = this.collectionRepository.findByIdAndStatus(id, true);
                    if (optionalPrintCollection.isPresent()) {
                        PrintCollection collection = optionalPrintCollection.get();
                        collection.setStatus(false);
                        collection.setLastUpdatedOn(new Date());
                        collections.add(collection);
                    } else log.error("CollectionService :: changeCollectionStatus : Print collection Not Found with id: " + id);
                });
        this.collectionRepository.saveAll(collections);
    }
}
