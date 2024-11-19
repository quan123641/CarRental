package com.hoaiphong.carrental.config;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                Authentication authentication) throws IOException, ServletException {
            String redirectUrl = null;

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                if (grantedAuthority.getAuthority().equals("ROLE_CUSTOMER")) {
                    redirectUrl = "/customer";
                    break;
                } else if (grantedAuthority.getAuthority().equals("ROLE_OWNER")) {
                    redirectUrl = "/owner";
                    break;
                }
            }

            // Nếu không tìm thấy quyền nào phù hợp, chuyển hướng đến một URL mặc định (ví dụ: trang chủ)
            if (redirectUrl == null) {
                redirectUrl = "/";  // URL mặc định
                // Hoặc bạn có thể ghi log và xử lý trường hợp này kỹ hơn
                // logger.warn("No valid role found for user: " + authentication.getName());
            }

            new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
        }

}