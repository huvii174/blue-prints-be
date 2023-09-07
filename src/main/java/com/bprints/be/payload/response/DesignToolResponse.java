package com.bprints.be.payload.response;

import com.bprints.be.dtos.DesignToolDto;
import lombok.Data;

import java.util.List;

@Data
public class DesignToolResponse extends ResponseBase{
    private List<DesignToolDto> designToolDtoList;
}
