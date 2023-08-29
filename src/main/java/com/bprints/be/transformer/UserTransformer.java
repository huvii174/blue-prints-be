package com.bprints.be.transformer;

import com.bprints.be.dtos.UserDTO;
import com.bprints.be.entities.User;
import com.bprints.be.payload.request.SignupRequest;

public class UserTransformer {
    public static UserDTO toDTO(User entity) {
        return UserDTO.builder()
                .userId(entity.getId())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .password(entity.getPassword())
                .username(entity.getUsername())
                .walletId(entity.getWalletId())
                .build();
    }

    /**
     * New Entity no need set ID
     * @param dto
     * @return
     */
    public static User toNewEntity(UserDTO dto) {
        User entity = new User();
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setUsername(dto.getUsername());
        entity.setWalletId(dto.getWalletId());
        return entity;
    }

    public static User toNewEntity(SignupRequest request, String encodePassword) {
        User entity = new User();
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setPassword(encodePassword);
        entity.setUsername(request.getUsername());
        entity.setWalletId(request.getWalletId());
        return entity;
    }
}
