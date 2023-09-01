package com.bprints.be.transformer;

import com.bprints.be.dtos.UserDTO;
import com.bprints.be.entities.ERole;
import com.bprints.be.entities.Role;
import com.bprints.be.entities.User;
import com.bprints.be.entities.Wallet;
import com.bprints.be.payload.request.SignupRequest;

import java.math.BigDecimal;

public class UserTransformer {
    public static UserDTO toDTO(User entity) {
        return UserDTO.builder()
                .userId(entity.getId())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .password(entity.getPassword())
                .username(entity.getUsername())
                .walletId(entity.getWallet().getId())
                .build();
    }

    /**
     * New Entity no need set ID
     * @param dto
     * @return
     */
    public static User toNewCustomerUserEntity(UserDTO dto) {
        User entity = new User();
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setUsername(dto.getUsername());
//        entity.set(dto.getWalletId());
        return entity;
    }

    public static User toNewCustomerUserEntity(SignupRequest request, String encodePassword) {
        User entity = new User();
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setPassword(encodePassword);
        entity.setUsername(request.getUsername());
        entity.setRole(builCustomerRole());
        Wallet wallet = new Wallet(BigDecimal.ZERO, entity);
        entity.setWallet(wallet);
        return entity;
    }

    public static User toNewAdminUserEntity(SignupRequest request, String encodePassword) {
        User entity = new User();
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setPassword(encodePassword);
        entity.setUsername(request.getUsername());
        entity.setRole(builAdminRole());
        entity.setWallet(new Wallet(BigDecimal.ZERO, entity));
        return entity;
    }

    private static Role builCustomerRole() {
        Role role = new Role();
        role.setId((long) ERole.ROLE_USER.id);
        role.setName(ERole.ROLE_USER);
        return role;
    }

    private static Role builAdminRole() {
        Role role = new Role();
        role.setId((long) ERole.ROLE_ADMIN.id);
        role.setName(ERole.ROLE_ADMIN);
        return role;
    }
}
