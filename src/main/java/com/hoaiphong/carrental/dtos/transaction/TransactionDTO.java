package com.hoaiphong.carrental.dtos.transaction;

import java.time.LocalDate;
import java.util.UUID;

import com.hoaiphong.carrental.dtos.user.UserDTOBase;
import com.hoaiphong.carrental.entities.User;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private UUID id;

    @NotBlank
    private double amount;

    @NotBlank
    private String type;
    
    private LocalDate dateTime;

    private String bookingNo;

    private String carName;

    private UUID userId;

    private String email;

    private UserDTOBase user;

}