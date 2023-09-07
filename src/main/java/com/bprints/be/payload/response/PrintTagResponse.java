package com.bprints.be.payload.response;

import com.bprints.be.dtos.PrintTagDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrintTagResponse extends ResponseBase {
    private List<PrintTagDto> printTagDtoList;
}
