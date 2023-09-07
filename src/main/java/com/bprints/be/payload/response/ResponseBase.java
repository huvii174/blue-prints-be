package com.bprints.be.payload.response;

import lombok.Data;

@Data
public class ResponseBase {
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
}
