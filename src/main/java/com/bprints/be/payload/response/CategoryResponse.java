package com.bprints.be.payload.response;

import com.bprints.be.dtos.PrintCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private List<PrintCategoryDto> categoryDtoList;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
}
