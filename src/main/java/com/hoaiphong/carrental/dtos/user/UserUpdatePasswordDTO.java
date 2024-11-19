package com.hoaiphong.carrental.dtos.user;

import java.util.UUID;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdatePasswordDTO {
    private UUID id;

    private String password;
}