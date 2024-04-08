package com.example.mqtt_backend.controller;

import com.example.mqtt_backend.constant.ResourcePath;
import com.example.mqtt_backend.modal.dto.UserLoginForm;
import com.example.mqtt_backend.service.UsersService;
import jakarta.websocket.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {


    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(ResourcePath.LOGIN)
    public String login(Model model) {
        UserLoginForm userLoginForm = new UserLoginForm();
        model.addAttribute("userLoginForm", userLoginForm);
        return ResourcePath.LOGIN_PAGE;
    }

    @PostMapping(ResourcePath.LOGIN )
    public String userLogin(@ModelAttribute UserLoginForm userLoginForm, Model model) {
        System.out.println(userLoginForm.getPassword());
        String outCome = usersService.UserLogin(userLoginForm.getEmail(), userLoginForm.getPassword());
        System.out.println(outCome);
        return switch (outCome) {
            case ResourcePath.LOGIN_SUCCESS -> ResourcePath.HOME_PAGE_URL;
            case ResourcePath.LOGIN_PASSWORD_INCORRECT -> {
                model.addAttribute("error", "Password incorrect");
                yield ResourcePath.LOGIN_FAILED;
            }
            case ResourcePath.LOGIN_EMAIL_NOT_FOUND -> {
                model.addAttribute("error", "Email not found");
                yield ResourcePath.LOGIN_FAILED;
            }
            default -> {
                model.addAttribute("error", "Login Failed");
                yield ResourcePath.LOGIN_FAILED;
            }
        };
    }

}
