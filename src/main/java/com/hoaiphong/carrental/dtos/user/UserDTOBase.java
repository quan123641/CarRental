package com.hoaiphong.carrental.dtos.user;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import com.hoaiphong.carrental.dtos.user.unique.UniqueEmail;
import com.hoaiphong.carrental.dtos.user.unique.UniqueUsername;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTOBase {

    private UUID id;

    @NotBlank(message = "Name is required")
    private String name;

    private LocalDate dateOfBirth;

    @Length(min = 9, max = 12, message = "National id must be between 9 and 12 characters")
    private String nationalId;

    @NotBlank(message = "Phone is required")
    @Length(min = 9, max = 12, message = "Phone must be between 9 and 12 characters")
    private String phone;

    @NotBlank(message = "Email is required")
    @Length(min = 5, max = 50, message = "Email must be between 5 and 50 characters")
    @UniqueEmail
    private String email;

    @Length(min = 10, max = 100, message = "Address must be between 10 and 100 characters")
    private String address;

    @Length(min = 9, max = 12, message = "Driving license must be between 9 and 12 characters")
    private String drivingLicense;

    private Double wallet;

    @NotBlank(message = "Username is required")
    @Length(min = 5, max = 50, message = "Username must be between 5 and 50 characters")
    @UniqueUsername
    private String username;

    @NotBlank(message = "Password is required")
    @Length(min = 5, max = 50, message = "Password must be between 5 and 50 characters")
    private String password;

    @NotEmpty(message = "Role is required") // Đảm bảo tập hợp không rỗng
    @Valid // Đảm bảo các phần tử trong tập hợp cũng được xác thực
    private Set<@NotBlank(message = "Role name cannot be blank") String> roleName;

    private Set<RoleDTO> roles;
}