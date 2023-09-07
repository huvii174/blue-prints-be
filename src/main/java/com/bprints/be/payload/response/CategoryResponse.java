package com.bprints.be.payload.response;

import com.bprints.be.dtos.PrintCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class CategoryResponse extends ResponseBase {
    private List<PrintCategoryDto> categoryDtoList;
}
