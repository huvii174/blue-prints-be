package com.bprints.be.entities;

import lombok.Getter;

@Getter
public enum ERole {
    ROLE_USER(1000),
    ROLE_MODERATOR(2000),
    ROLE_ADMIN(3000);

    public int id;

    ERole(int id) {
        this.id = id;
    }

    public static ERole of(int code) {
        switch (code) {
            case 1000:
                return ROLE_USER;
            case 2000:
                return ROLE_MODERATOR;
            case 3000:
                return ROLE_ADMIN;
            default:
                throw new IllegalArgumentException("unknown ERole:" + code);
        }
    }
}
