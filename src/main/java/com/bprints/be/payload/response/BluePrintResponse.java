package com.bprints.be.payload.response;

import com.bprints.be.dtos.BluePrintDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class BluePrintResponse extends ResponseBase{
    private List<BluePrintDto> bluePrintList;
}
