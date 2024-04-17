package com.example.mqtt_backend.modal.util;
import com.example.mqtt_backend.service.CustomUserDetail;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

public class LoginUserUtil {
    public static void loadLoginUser(Model model){
        try{
            CustomUserDetail userDetails = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("userName", userDetails.getLoginUserName());
            model.addAttribute("userRole", userDetails.getRole());


        }catch (Exception e){
            System.out.println("Load login user fail: " + e);
        }
    }
}
