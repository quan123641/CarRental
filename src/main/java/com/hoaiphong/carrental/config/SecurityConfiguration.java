package com.hoaiphong.carrental.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    @Autowired
    private CustomSuccessHandler customSuccessHandler; // Tự động nạp CustomSuccessHandler

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/detail").permitAll()
                        .requestMatchers("/search/**").hasAuthority("ROLE_CUSTOMER")
                        .requestMatchers("/bookingInformation/**").hasAuthority("ROLE_CUSTOMER")
                        .requestMatchers("/bookingPayment/**").hasAuthority("ROLE_CUSTOMER")
                        .requestMatchers("/bookingFinish").hasAuthority("ROLE_CUSTOMER")


                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/error/**").permitAll()
                        .requestMatchers("/car/**").hasAuthority("ROLE_OWNER")
                        .requestMatchers("/customer/**").hasAuthority("ROLE_CUSTOMER") // Chỉ cho phép CUSTOMER truy cập vào các URL bắt đầu bằng /customer/**
                        .requestMatchers("/owner/**").hasAuthority("ROLE_OWNER") // Chỉ cho phép OWNER truy cập vào các URL bắt đầu bằng /owner/**
                        .requestMatchers("/processPayment/**").permitAll()

                        
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .successHandler(customSuccessHandler) // Sử dụng CustomSuccessHandler ở đây
                        .failureUrl("/auth/login?errorMessage=Email or Password incorrect"))
                .logout(LogoutConfigurer::permitAll)
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(accessDeniedHandler()));
        return http.build();
    }

    public AccessDeniedHandler accessDeniedHandler() {
        AccessDeniedHandlerImpl accessDeniedHandler = new AccessDeniedHandlerImpl();
        accessDeniedHandler.setErrorPage("/auth/access-denied");
        return accessDeniedHandler;
    }
}
