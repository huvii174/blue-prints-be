package com.bprints.be.payload.response;

import com.bprints.be.dtos.BluePrintDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BluePrintResponse {
    private List<BluePrintDto> bluePrintList;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
}
