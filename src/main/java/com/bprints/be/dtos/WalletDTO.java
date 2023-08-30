package com.bprints.be.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class WalletDTO {
    private Long walletId;
    private BigDecimal balance;
    private Long userId;
}
