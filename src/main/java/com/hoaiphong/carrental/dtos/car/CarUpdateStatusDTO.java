package com.hoaiphong.carrental.dtos.car;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarUpdateStatusDTO {
    private UUID id;
    @NotNull
    private String status;
}
