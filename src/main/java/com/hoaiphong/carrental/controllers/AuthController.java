package com.hoaiphong.carrental.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hoaiphong.carrental.dtos.user.UserDTOBase;
import com.hoaiphong.carrental.entities.PasswordResetToken;
import com.hoaiphong.carrental.entities.User;
import com.hoaiphong.carrental.repositories.TokenRepository;
import com.hoaiphong.carrental.repositories.UserRepository;
import com.hoaiphong.carrental.services.AuthService;
import com.hoaiphong.carrental.services.RoleService;
import com.hoaiphong.carrental.services.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class AuthController {

    private final AuthService authService;
    private final RoleService roleService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public AuthController(AuthService authService, RoleService roleService,  UserRepository userRepository, UserService userService, TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
        this.userService = userService;
        this.roleService = roleService;
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @GetMapping("/auth/login")
    public String login(Model model, @RequestParam(required = false) String errorMessage) {
        // Nếu có thông báo lỗi từ yêu cầu, thêm nó vào mô hình
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }

        model.addAttribute("userDTOBase", new UserDTOBase());
        return "auth/login";
    }

    @GetMapping("/auth/resetpassword")
    public String signup() {
        return "auth/resetpassword";
    }
    @PostMapping("/auth/resetpassword")
    public String forgotPassordProcess(@ModelAttribute UserDTOBase userDTOBase) {
        String output = "";
        User user = userRepository.findByEmail(userDTOBase.getEmail());
        if (user != null) {
			output = userService.sendEmail(user);
		}
		if (output.equals("success")) {
			return "redirect:/auth/resetpassword?success";
		}

        return "redirect:/auth/login?error";
    }

    @PostMapping("/auth/register")
    public String register(@ModelAttribute("userDTOBase") @Valid UserDTOBase userDTOBase, BindingResult result) {

        if (result.hasErrors()) {
            return "auth/login";
        }

        authService.save(userDTOBase);
        return "redirect:/auth/login";
    }

    @GetMapping("/auth/changepassword/{token}")
    public String changePassword(@PathVariable String token, Model model) {
        PasswordResetToken reset = tokenRepository.findByToken(token);
		if (reset != null && userService.hasExipred(reset.getExpiryDateTime())) {
			model.addAttribute("email", reset.getUser().getEmail());
			return "auth/changePassword";
		}
		return "redirect:/auth/resetpassword?error";
    }

    @PostMapping("/auth/changepassword")
    public String changePasswordProcess(@ModelAttribute UserDTOBase userDTOBase) {
        User user = userRepository.findByEmail(userDTOBase.getEmail());
		if(user != null) {
			user.setPassword(passwordEncoder.encode(userDTOBase.getPassword()));
			userRepository.save(user);
		}
		return "redirect:/auth/login";
    }

    @GetMapping("/auth/access-denied")
    public String accessDenied() {
        return "auth/access-denied";
    }

}