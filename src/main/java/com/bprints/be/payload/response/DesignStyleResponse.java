package com.bprints.be.payload.response;

import com.bprints.be.dtos.DesignStyleDto;
import lombok.Data;

import java.util.List;

@Data
public class DesignStyleResponse extends ResponseBase{
    private List<DesignStyleDto> designStyleDtoList;
}
