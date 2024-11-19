package com.hoaiphong.carrental.dtos.user;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateWalletDTO {
    private UUID id;

    @NotBlank(message = "")
    private Double wallet;

}
