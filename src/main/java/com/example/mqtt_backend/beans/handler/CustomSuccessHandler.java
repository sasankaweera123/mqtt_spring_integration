package com.example.mqtt_backend.beans.handler;

import com.example.mqtt_backend.constant.ResourcePath;
import com.example.mqtt_backend.enumeration.UserRole;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        var authorities = authentication.getAuthorities();
        var roles = authorities.stream().map(GrantedAuthority::getAuthority).findFirst();

        if(roles.orElse("").equals(UserRole.ADMIN.name())) {
            response.sendRedirect("/"+ResourcePath.DASHBOARD);
        }
        else if(roles.orElse("").equals(UserRole.BANKUSER.name())){
            response.sendRedirect("/"+ResourcePath.SOUND_BOX);
        } else{
            response.sendRedirect("/error");
        }
    }
}
