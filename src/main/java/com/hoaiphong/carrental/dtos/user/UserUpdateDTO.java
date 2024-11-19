package com.hoaiphong.carrental.dtos.user;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
    private UUID id;

    @NotBlank(message = "")
    private String fullName;

    @NotBlank(message = "")
    private String phone;

    @NotBlank(message = "")
    private String nationalId;

    private LocalDate dateOfBirth;

    private String drivingLicense;

    @NotBlank(message = "")
    private String address;
}
