package com.bprints.be.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private String password;
    private String phone;
    private Long walletId;
}
