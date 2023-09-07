package com.bprints.be.services;

import com.bprints.be.dtos.DesignToolDto;
import com.bprints.be.entities.DesignTool;
import com.bprints.be.payload.response.DesignToolResponse;
import com.bprints.be.repositories.DesignToolRepository;
import com.bprints.be.transformer.DesignToolTransformer;
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
public class DesignToolServiceImpl implements DesignToolService {
    private DesignToolRepository designToolRepository;

    @Override
    public void saveTool(DesignToolDto designToolDto) {
        log.info("DesignToolService :: saveTool request -> designToolDto {}", designToolDto);
        DesignTool designTool = DesignToolTransformer.toEntity(designToolDto);

        //Update case
        if (Objects.nonNull(designToolDto.getId())) {
            Long id = designToolDto.getId();
            Optional<DesignTool> optionalDesignTool = this.designToolRepository.findByIdAndStatus(id, true);
            if (optionalDesignTool.isEmpty()) {
                log.error("DesignToolService :: saveTool : Tool Not Found with id: " + id);
            }
            else designTool.setId(id);
        }

        this.designToolRepository.save(designTool);
    }

    @Override
    public DesignToolDto findById(Long id) {
        log.info("DesignToolService :: findById request id {}", id);
        Optional<DesignTool> optionalDesignTool = this.designToolRepository.findByIdAndStatus(id, true);
        if (optionalDesignTool.isEmpty()) {
            log.error("DesignToolService :: findById : Tool Not Found with id: " + id);
            return null;
        }
        DesignTool designTool = optionalDesignTool.get();
        DesignToolDto designToolDto = DesignToolTransformer.toDto(designTool);

        return designToolDto;
    }

    @Override
    public DesignToolResponse findAll(Pageable pageable) {
        log.info("DesignToolService :: findAll");
        Page<DesignTool> page = this.designToolRepository.findByStatus(pageable, true);
        List<DesignToolDto> designToolDtoList = page.getContent().stream()
                .map(tool -> DesignToolTransformer.toDto(tool))
                .collect(Collectors.toList());

        DesignToolResponse designToolResponse = new DesignToolResponse();
        designToolResponse.setDesignToolDtoList(designToolDtoList);
        designToolResponse.setPageNo(page.getNumber());
        designToolResponse.setPageSize(page.getSize());
        designToolResponse.setTotalElements(page.getTotalElements());
        designToolResponse.setTotalPages(page.getTotalPages());
        return designToolResponse;
    }

    @Override
    public void changeToolStatus(List<Long> toolIdList) {
        log.info("DesignToolService :: changeToolStatus request toolIdList {}", Arrays.toString(toolIdList.toArray()));
        List<DesignTool> designToolList = new ArrayList<>();
        toolIdList.stream()
                .forEach(id -> {
                    Optional<DesignTool> optionalDesignTool = this.designToolRepository.findByIdAndStatus(id, true);
                    if (optionalDesignTool.isPresent()) {
                        DesignTool designTool = optionalDesignTool.get();
                        designTool.setStatus(false);
                        designTool.setLastUpdatedOn(new Date());
                        designToolList.add(designTool);
                    } else log.error("DesignToolService :: changeToolStatus : Tool Not Found with id: " + id);
                });
        this.designToolRepository.saveAll(designToolList);
    }
}
