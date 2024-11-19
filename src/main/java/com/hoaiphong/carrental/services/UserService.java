package com.hoaiphong.carrental.services;

import java.util.*;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hoaiphong.carrental.dtos.user.UserDTOBase;
import com.hoaiphong.carrental.dtos.user.UserUpdateDTO;
import com.hoaiphong.carrental.dtos.user.UserUpdatePasswordDTO;
import com.hoaiphong.carrental.dtos.user.UserUpdateWalletDTO;
import com.hoaiphong.carrental.entities.User;

public interface UserService {
    List<UserDTOBase> findAll();

    Page<UserDTOBase> findAll(String keyword, Pageable pageable);

    UserDTOBase findById(UUID id);

    UserDTOBase findByEmail(String email);
    UserDTOBase create(UserDTOBase userDTOBase);

    UserDTOBase update(UUID id, UserDTOBase userDTOBase);

    UserDTOBase updatePassword(UUID id, String password);

    boolean deleteById(UUID id);

    UserUpdateDTO update(UserUpdateDTO userUpdateDTO, String email);

    UserDTOBase update(UserUpdateWalletDTO userUpdateWalletDTO, String email);

    UserDTOBase update(UserUpdatePasswordDTO userUpdatePasswordDTO, String email);

    String sendEmail(User user);

    boolean hasExipred(LocalDateTime expiryDateTime);

    User findByUsername(String currentUsername);

    void update(User currentUser, String currentUsername);
}