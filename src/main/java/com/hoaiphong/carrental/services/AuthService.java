package com.hoaiphong.carrental.services;

import java.util.UUID;

import com.hoaiphong.carrental.dtos.user.UserDTOBase;

public interface AuthService {
    UUID save(UserDTOBase userDTOBase); // Save user

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    UserDTOBase findByEmail(String email);
    
}