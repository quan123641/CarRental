package com.hoaiphong.carrental.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoaiphong.carrental.dtos.user.RoleDTO;
import com.hoaiphong.carrental.dtos.user.UserDTOBase;
import com.hoaiphong.carrental.dtos.user.UserUpdateDTO;
import com.hoaiphong.carrental.dtos.user.UserUpdatePasswordDTO;
import com.hoaiphong.carrental.dtos.user.UserUpdateWalletDTO;
import com.hoaiphong.carrental.entities.PasswordResetToken;
import com.hoaiphong.carrental.entities.Role;
import com.hoaiphong.carrental.entities.User;
import com.hoaiphong.carrental.repositories.RoleRepository;
import com.hoaiphong.carrental.repositories.TokenRepository;
import com.hoaiphong.carrental.repositories.UserRepository;
import com.hoaiphong.carrental.services.UserService;

import jakarta.persistence.criteria.Predicate;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final com.hoaiphong.carrental.repositories.UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder, JavaMailSender javaMailSender, TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public List<UserDTOBase> findAll() {
        // Get List of entities
        var users = userRepository.findAll();

        // Convert entities to DTOs
        var userDTOBases = users.stream().map(user -> {
            var userDTOBase = new UserDTOBase();
            userDTOBase.setId(user.getId());
            userDTOBase.setName(user.getName());
            userDTOBase.setDateOfBirth(user.getDateOfBirth());
            userDTOBase.setNationalId(user.getNationalId());
            userDTOBase.setPhone(user.getPhone());
            userDTOBase.setAddress(user.getAddress());
            userDTOBase.setEmail(user.getEmail());
            userDTOBase.setDrivingLicense(user.getDrivingLicense());
            userDTOBase.setWallet(user.getWallet());
            userDTOBase.setUsername(user.getUsername());
            userDTOBase.setEmail(user.getEmail());

            // Convert roles to role
            Set<RoleDTO> roleDTOs = user.getRoles().stream().map(role -> {
                var roleDTO = new RoleDTO();
                roleDTO.setId(role.getId());
                roleDTO.setName(role.getName());
                return roleDTO;
            }).collect(Collectors.toSet());

            userDTOBase.setRoles(roleDTOs);
            userDTOBase.setRoleName(roleDTOs.stream().map(RoleDTO::getName).collect(Collectors.toSet()));
            return userDTOBase;
        }).toList();

        // Return DTOs
        return userDTOBases;
    }

    @Override
    public Page<UserDTOBase> findAll(String keyword, Pageable pageable) {
        // Find user by keyword
        Specification<User> specification = (root, query, criteriaBuilder) -> {
            // Neu keyword null thi tra ve null
            if (keyword == null) {
                return null;
            }

            // WHERE LOWER(name) LIKE %keyword%
            Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                    "%" + keyword.toLowerCase() + "%");

            // WHERE LOWER(userName) LIKE %keyword%
            Predicate userNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("username")),
                    "%" + keyword.toLowerCase() + "%");

            // WHERE LOWER(email) LIKE %keyword%
            Predicate emailPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("email")),
                    "%" + keyword.toLowerCase() + "%");

            // WHERE LOWER(name) LIKE %keyword% OR LOWER(description) LIKE %keyword% OR
            // LOWER(username) LIKE %keyword% OR LOWER(email) LIKE %keyword%
            return criteriaBuilder.or(namePredicate, userNamePredicate, emailPredicate);
        };

        var users = userRepository.findAll(specification, pageable);

        // Covert Page<User> to Page<UserDTOBase>
        var userDTOBases = users.map(user -> {
            var userDTOBase = new UserDTOBase();
            userDTOBase.setId(user.getId());
            userDTOBase.setName(user.getName());
            userDTOBase.setDateOfBirth(user.getDateOfBirth());
            userDTOBase.setNationalId(user.getNationalId());
            userDTOBase.setPhone(user.getPhone());
            userDTOBase.setAddress(user.getAddress());
            userDTOBase.setEmail(user.getEmail());
            userDTOBase.setDrivingLicense(user.getDrivingLicense());
            userDTOBase.setWallet(user.getWallet());
            userDTOBase.setUsername(user.getUsername());
            userDTOBase.setEmail(user.getEmail());
            // Convert roles to roles
            Set<RoleDTO> roleDTOs = user.getRoles().stream().map(role -> {
                var roleDTO = new RoleDTO();
                roleDTO.setId(role.getId());
                roleDTO.setName(role.getName());
                return roleDTO;
            }).collect(Collectors.toSet());

            userDTOBase.setRoles(roleDTOs);
            userDTOBase.setRoleName(roleDTOs.stream().map(RoleDTO::getName).collect(Collectors.toSet()));

            return userDTOBase;
        });

        return userDTOBases;
    }

    @Override
    public UserDTOBase findById(UUID id) {
        // Get entity by id
        var user = userRepository.findById(id).orElse(null);

        // Check if entity is null then return null
        if (user == null) {
            return null;
        }

        // Convert entity to DTO
        var userDTOBase = new UserDTOBase();
        userDTOBase.setId(user.getId());
        userDTOBase.setId(user.getId());
        userDTOBase.setName(user.getName());
        userDTOBase.setDateOfBirth(user.getDateOfBirth());
        userDTOBase.setNationalId(user.getNationalId());
        userDTOBase.setPhone(user.getPhone());
        userDTOBase.setAddress(user.getAddress());
        userDTOBase.setEmail(user.getEmail());
        userDTOBase.setDrivingLicense(user.getDrivingLicense());
        userDTOBase.setWallet(user.getWallet());
        userDTOBase.setUsername(user.getUsername());
        userDTOBase.setEmail(user.getEmail());
        // Convert roles to roles
        Set<RoleDTO> roleDTOs = user.getRoles().stream().map(role -> {
            var roleDTO = new RoleDTO();
            roleDTO.setId(role.getId());
            roleDTO.setName(role.getName());
            return roleDTO;
        }).collect(Collectors.toSet());

        userDTOBase.setRoles(roleDTOs);
        userDTOBase.setRoleName(roleDTOs.stream().map(RoleDTO::getName).collect(Collectors.toSet()));

        // Return DTO
        return userDTOBase;
    }

    @Override
    public UserDTOBase findByEmail(String email) {
        var user = userRepository.findByEmail(email);
        if (user == null) {
            return null;
        }
        var userDTOBase = new UserDTOBase();
        userDTOBase.setId(user.getId());
        userDTOBase.setId(user.getId());
        userDTOBase.setName(user.getName());
        userDTOBase.setDateOfBirth(user.getDateOfBirth());
        userDTOBase.setNationalId(user.getNationalId());
        userDTOBase.setPhone(user.getPhone());
        userDTOBase.setAddress(user.getAddress());
        userDTOBase.setEmail(user.getEmail());
        userDTOBase.setDrivingLicense(user.getDrivingLicense());
        userDTOBase.setWallet(user.getWallet());
        userDTOBase.setUsername(user.getUsername());
        userDTOBase.setEmail(user.getEmail());
        // Convert roles to roles
        Set<RoleDTO> roleDTOs = user.getRoles().stream().map(role -> {
            var roleDTO = new RoleDTO();
            roleDTO.setId(role.getId());
            roleDTO.setName(role.getName());
            return roleDTO;
        }).collect(Collectors.toSet());

        userDTOBase.setRoles(roleDTOs);
        userDTOBase.setRoleName(roleDTOs.stream().map(RoleDTO::getName).collect(Collectors.toSet()));
        return userDTOBase;
    }

    @Override
    public UserDTOBase create(UserDTOBase userDTOBase) {
        // Check if userDTOBase is null then throw exception
        if (userDTOBase == null) {
            throw new IllegalArgumentException("User is required");
        }

        // Check if user with the same username exists
        var user = userRepository.findByUsername(userDTOBase.getUsername());

        if (user != null) {
            throw new IllegalArgumentException("User with the same username already exists");
        }

        // Convert DTO to entity
        user = new User();
        user.setId(userDTOBase.getId());
        user.setId(userDTOBase.getId());
        user.setName(userDTOBase.getName());
        user.setDateOfBirth(userDTOBase.getDateOfBirth());
        user.setNationalId(userDTOBase.getNationalId());
        user.setPhone(userDTOBase.getPhone());
        user.setAddress(userDTOBase.getAddress());
        user.setEmail(userDTOBase.getEmail());
        user.setDrivingLicense(userDTOBase.getDrivingLicense());
        user.setWallet(userDTOBase.getWallet());
        user.setUsername(userDTOBase.getUsername());
        user.setEmail(userDTOBase.getEmail());
        user.setPassword(passwordEncoder.encode(userDTOBase.getPassword()));

        if (userDTOBase.getRoleName() != null && !userDTOBase.getRoleName().isEmpty()) {

            var existingRoles = roleRepository.findAll();

            Set<Role> roles = userDTOBase.getRoleName().stream()
                    .map(roleName -> {
                        var existedRole = existingRoles.stream()
                                .filter(role -> role.getName().equals(roleName))
                                .findFirst()
                                .orElse(null);

                        if (existedRole == null) {
                            throw new IllegalArgumentException("Role with id " + roleName + " does not exist");
                        }

                        return existedRole;
                    })
                    .collect(Collectors.toSet());

            user.setRoles(roles);
        }

        // Save entity
        user = userRepository.save(user);

        // Convert entity to DTO
        var newUserDTOBase = new UserDTOBase();
        newUserDTOBase.setId(user.getId());
        newUserDTOBase.setId(user.getId());
        newUserDTOBase.setName(user.getName());
        newUserDTOBase.setDateOfBirth(user.getDateOfBirth());
        newUserDTOBase.setNationalId(user.getNationalId());
        newUserDTOBase.setPhone(user.getPhone());
        newUserDTOBase.setAddress(user.getAddress());
        newUserDTOBase.setEmail(user.getEmail());
        newUserDTOBase.setDrivingLicense(user.getDrivingLicense());
        newUserDTOBase.setWallet(user.getWallet());
        newUserDTOBase.setUsername(user.getUsername());
        newUserDTOBase.setEmail(user.getEmail());
        newUserDTOBase.setPassword(user.getPassword());

        // Return DTO
        return newUserDTOBase;
    }

    @Override
    public UserDTOBase update(UUID id, UserDTOBase userDTOBase) {
        // Check if userDTOBase is null then throw exception
        if (userDTOBase == null) {
            throw new IllegalArgumentException("User is required");
        }

        // Get entity by id
        var user = userRepository.findById(id).orElse(null);

        // Check if entity is null then return null
        if (user == null) {
            return null;
        }

        // Check if user with the same username exists
        var userWithSameUsername = userRepository.findByUsername(userDTOBase.getUsername());

        if (userWithSameUsername != null && !userWithSameUsername.getId().equals(id)) {
            throw new IllegalArgumentException("User with the same username already exists");
        }

        // Update entity
        user.setId(userDTOBase.getId());
        user.setId(userDTOBase.getId());
        user.setName(userDTOBase.getName());
        user.setDateOfBirth(userDTOBase.getDateOfBirth());
        user.setNationalId(userDTOBase.getNationalId());
        user.setPhone(userDTOBase.getPhone());
        user.setAddress(userDTOBase.getAddress());
        user.setEmail(userDTOBase.getEmail());
        user.setDrivingLicense(userDTOBase.getDrivingLicense());
        user.setWallet(userDTOBase.getWallet());
        user.setUsername(userDTOBase.getUsername());
        user.setEmail(userDTOBase.getEmail());
        // Set Role to entity

        if (userDTOBase.getRoleName() != null && !userDTOBase.getRoleName().isEmpty()) {

            user.getRoles().clear();

            var existingRoles = roleRepository.findAll();

            Set<Role> roles = userDTOBase.getRoleName().stream()
                    .map(roleName -> {
                        var existedRole = existingRoles.stream()
                                .filter(role -> role.getName().equals(roleName))
                                .findFirst()
                                .orElse(null);

                        if (existedRole == null) {
                            throw new IllegalArgumentException("Role with id " + roleName + " does not exist");
                        }

                        return existedRole;
                    })
                    .collect(Collectors.toSet());

            user.setRoles(roles);
        }

        // Save entity
        user = userRepository.save(user);

        // Convert entity to DTO
        userDTOBase.setId(user.getId());

        // Return DTO
        return userDTOBase;
    }

    @Override
    public UserDTOBase updatePassword(UUID id, String password) {
        // Check if password is null then throw exception
        if (password == null) {
            throw new IllegalArgumentException("Password is required");
        }

        // Get entity by id
        var user = userRepository.findById(id).orElse(null);

        // Check if entity is null then return null
        if (user == null) {
            return null;
        }

        // Update entity
        user.setPassword(passwordEncoder.encode(password));

        // Save entity
        user = userRepository.save(user);

        // Convert entity to DTO
        var userDTOBase = new UserDTOBase();
        userDTOBase.setId(user.getId());
        userDTOBase.setId(user.getId());
        userDTOBase.setName(user.getName());
        userDTOBase.setDateOfBirth(user.getDateOfBirth());
        userDTOBase.setNationalId(user.getNationalId());
        userDTOBase.setPhone(user.getPhone());
        userDTOBase.setAddress(user.getAddress());
        userDTOBase.setEmail(user.getEmail());
        userDTOBase.setDrivingLicense(user.getDrivingLicense());
        userDTOBase.setWallet(user.getWallet());
        userDTOBase.setUsername(user.getUsername());
        userDTOBase.setEmail(user.getEmail());

        // Return DTO
        return userDTOBase;
    }

    @Override
    public boolean deleteById(UUID id) {
        // Check if entity exists
        var user = userRepository.findById(id).orElse(null);

        // Check if entity is null then return
        if (user == null) {
            return false;
        }

        // Delete entity
        userRepository.delete(user);

        // Check if entity is deleted
        var isDeleted = userRepository.findById(id).isEmpty();

        // Return if entity is deleted
        return isDeleted;
    }

    @Override
    public UserUpdateDTO update(UserUpdateDTO userUpdateDTO, String email) {
        var existingMember = userRepository.findByEmail(email);
        if (existingMember == null) {
            throw new IllegalArgumentException("User with email " + email + " does not exist");
        }

        // Update fields with data from DTO
        existingMember.setName(userUpdateDTO.getFullName());
        existingMember.setPhone(userUpdateDTO.getPhone());
        existingMember.setNationalId(userUpdateDTO.getNationalId());
        existingMember.setDateOfBirth(userUpdateDTO.getDateOfBirth());
        existingMember.setAddress(userUpdateDTO.getAddress());
        // existingMember.setDrivingLicense(userUpdateDTO.getDrivingLicense());
        // Save the updated member
        var updateMember = userRepository.save(existingMember);

        // Convert updated entity to DTO and return
        var updateUserDTO = new UserUpdateDTO();
        updateUserDTO.setFullName(updateMember.getName());
        updateUserDTO.setPhone(updateMember.getPhone());
        updateUserDTO.setNationalId(updateMember.getNationalId());
        updateUserDTO.setDateOfBirth(updateMember.getDateOfBirth());
        updateUserDTO.setAddress(updateMember.getAddress());
        // updateUserDTO.setDrivingLicense(updateMember.getDrivingLicense());

        return updateUserDTO;
    }

    @Override
    public UserDTOBase update(UserUpdatePasswordDTO userUpdatePasswordDTO, String email) {
        var existingMember = userRepository.findByEmail(email);
        if (existingMember == null) {
            throw new IllegalArgumentException("User with email " + email + " does not exist");
        }

        // Update fields with data from DTO
        existingMember.setPassword(passwordEncoder.encode(userUpdatePasswordDTO.getPassword()));
        // Save the updated member
        var updateMember = userRepository.save(existingMember);

        // Convert updated entity to DTO and return
        var updateUserDTO = new UserDTOBase();
        updateUserDTO.setPassword(updateMember.getPassword());

        return updateUserDTO;
    }

    @Override
    public String sendEmail(User user) {
        try {
			String resetLink = generateResetToken(user);

			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom("phongnguyenhoai2003@gmail.com");// input the senders email ID
			msg.setTo(user.getEmail());

			msg.setSubject("Welcome To My Channel");
			msg.setText("Hello \n\n" + "Please click on this link to Reset your Password :" + resetLink + ". \n\n"
					+ "Regards \n" + "ABC");

			javaMailSender.send(msg);

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
    }

    private String generateResetToken(User user) {
        UUID uuid = UUID.randomUUID();
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDateTime expiryDateTime = currentDateTime.plusMinutes(30);
		PasswordResetToken resetToken = new PasswordResetToken();
		resetToken.setUser(user);
		resetToken.setToken(uuid.toString());
		resetToken.setExpiryDateTime(expiryDateTime);
		resetToken.setUser(user);
		PasswordResetToken token = tokenRepository.save(resetToken);
		if (token != null) {
			String endpointUrl = "http://localhost:8080/auth/changepassword";
			return endpointUrl + "/" + resetToken.getToken();
		}
		return "";
    }

    @Override
    public boolean hasExipred(LocalDateTime expiryDateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
		return expiryDateTime.isAfter(currentDateTime);
    }

    @Override
    public UserDTOBase update(UserUpdateWalletDTO userUpdateWalletDTO, String email) {
        var existingMember = userRepository.findByEmail(email);
        if (existingMember == null) {
            throw new IllegalArgumentException("User with email " + email + " does not exist");
        }

        // Update fields with data from DTO
        existingMember.setWallet(userUpdateWalletDTO.getWallet());
        // Save the updated member
        var updateMember = userRepository.save(existingMember);

        // Convert updated entity to DTO and return
        var updateUserDTO = new UserDTOBase();
        updateUserDTO.setWallet(updateMember.getWallet());
        // updateUserDTO.setDrivingLicense(updateMember.getDrivingLicense());

        return updateUserDTO;
    }

    @Override
    public User findByUsername(String currentUsername) {
        return userRepository.findByEmail(currentUsername);
    }

    @Override
    public void update(User currentUser, String currentUsername) {
        var user = userRepository.findByEmail(currentUsername);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        user.setWallet(currentUser.getWallet());
        userRepository.save(user);
    }

}