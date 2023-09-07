package com.bprints.be.services;

import com.bprints.be.dtos.DesignStyleDto;
import com.bprints.be.entities.DesignStyle;
import com.bprints.be.payload.response.DesignStyleResponse;
import com.bprints.be.repositories.DesignStyleRepository;
import com.bprints.be.transformer.DesignStyleTransformer;
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
public class DesignStyleServiceImpl implements DesignStyleService {
    private DesignStyleRepository designStyleRepository;

    @Override
    public void saveStyle(DesignStyleDto designStyleDto) {
        log.info("DesignStyleService :: saveStyle request -> designStyleDto {}", designStyleDto);
        DesignStyle designStyle = DesignStyleTransformer.toEntity(designStyleDto);

        //Update case
        if (Objects.nonNull(designStyleDto.getId())) {
            Long id = designStyleDto.getId();
            Optional<DesignStyle> optionalDesignStyle = this.designStyleRepository.findByIdAndStatus(id, true);
            if (optionalDesignStyle.isEmpty()) {
                log.error("DesignStyleService :: saveStyle : Tool Not Found with id: " + id);
            }
            designStyle.setId(id);
        }

        this.designStyleRepository.save(designStyle);
    }

    @Override
    public DesignStyleDto findById(Long id) {
        log.info("DesignStyleService :: findById request id {}", id);
        Optional<DesignStyle> optionalDesignStyle = this.designStyleRepository.findByIdAndStatus(id, true);
        if (optionalDesignStyle.isEmpty()) {
            log.error("DesignStyleService :: findById : Style Not Found with id: " + id);
            return null;
        }
        DesignStyle designStyle = optionalDesignStyle.get();
        DesignStyleDto designStyleDto = DesignStyleTransformer.toDto(designStyle);

        return designStyleDto;
    }

    @Override
    public DesignStyleResponse findAll(Pageable pageable) {
        log.info("DesignStyleService :: findAll");
        Page<DesignStyle> page = this.designStyleRepository.findByStatus(pageable, true);
        List<DesignStyleDto> designStyleDtoList = page.getContent().stream()
                .map(style -> DesignStyleTransformer.toDto(style))
                .collect(Collectors.toList());

        DesignStyleResponse styleResponse = new DesignStyleResponse();
        styleResponse.setDesignStyleDtoList(designStyleDtoList);
        styleResponse.setPageNo(page.getNumber());
        styleResponse.setPageSize(page.getSize());
        styleResponse.setTotalElements(page.getTotalElements());
        styleResponse.setTotalPages(page.getTotalPages());
        return styleResponse;
    }

    @Override
    public void changeStyleStatus(List<Long> styleIdList) {
        log.info("DesignStyleService :: changeStyleStatus request styleIdList {}", Arrays.toString(styleIdList.toArray()));
        List<DesignStyle> designStyleList = new ArrayList<>();
        styleIdList.stream()
                .forEach(id -> {
                    Optional<DesignStyle> optionalDesignStyle = this.designStyleRepository.findByIdAndStatus(id, true);
                    if (optionalDesignStyle.isPresent()) {
                        DesignStyle designStyle = optionalDesignStyle.get();
                        designStyle.setStatus(false);
                        designStyle.setLastUpdatedOn(new Date());
                        designStyleList.add(designStyle);
                    } else log.error("DesignStyleService :: changeStyleStatus : Style Not Found with id: " + id);
                });
        this.designStyleRepository.saveAll(designStyleList);
    }
}
