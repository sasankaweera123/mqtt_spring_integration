package com.example.mqtt_backend.controller;

import com.example.mqtt_backend.constant.ResourcePath;
import com.example.mqtt_backend.entity.LoginUsers;
import com.example.mqtt_backend.enumeration.UserRole;
import com.example.mqtt_backend.modal.dto.NewUserForm;
import com.example.mqtt_backend.modal.dto.UpdateUserForm;
import com.example.mqtt_backend.modal.dto.UserLoginForm;
import com.example.mqtt_backend.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

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


    @GetMapping(ResourcePath.USERHANDLING)
    public String users(Model model) {
        List<LoginUsers> user = usersService.getAllUsers();
        List<UserRole> userRoles = Arrays.asList(UserRole.values());
        UpdateUserForm updateUserForm = new UpdateUserForm();
        NewUserForm newUserForm = new NewUserForm();

        System.out.println(user);
        model.addAttribute("user_details", user);
        model.addAttribute("user_form", updateUserForm);
        model.addAttribute("new_user_form", newUserForm);
        model.addAttribute("user_role", userRoles);
        return ResourcePath.USERHANDLING_PAGE;
    }

    @PostMapping(ResourcePath.USER_UPDATE + "/{id}")
    public String updateUser(@PathVariable long id, @ModelAttribute UpdateUserForm userForm, RedirectAttributes model) {
        try {
            LoginUsers user = new LoginUsers();
            user.setEmail(userForm.getUpdateEmail());
            user.setUsername(userForm.getUpdateUsername());
            user.setUserRole(userForm.getUpdateUserRole());
            user.setPassword(userForm.getUpdatePassword());
            usersService.updateUser(id, user);
            model.addFlashAttribute("message", "Successfully updated user details");
        }
        catch (Exception e){
            model.addFlashAttribute("message", "Failed to update user details");
        }
        return "redirect:/" + ResourcePath.USERHANDLING;
    }

    @GetMapping(ResourcePath.USER_DELETE + "/{id}")
    public String deleteUser(@PathVariable long id, RedirectAttributes model) {
        try{
            usersService.deleteUser(id);
            model.addFlashAttribute("message", "Successfully deleted user details");
        }catch (Exception e) {
            model.addFlashAttribute("message", "Failed to delete user details");
        }
        return "redirect:/" + ResourcePath.USERHANDLING;
    }

    @PostMapping(ResourcePath.USER_ADD)
    public String addUser(@ModelAttribute NewUserForm newUserForm, RedirectAttributes model) {
        if(!newUserForm.getPassword().equals(newUserForm.getConfirmPassword())){
            throw new IllegalArgumentException("Password and Confirm Password does not match");
        }
        try {
            LoginUsers user = new LoginUsers();
            user.setEmail(newUserForm.getEmail());
            user.setUsername(newUserForm.getUsername());
            user.setUserRole(newUserForm.getUserRole());
            user.setPassword(newUserForm.getPassword());
            usersService.addUser(user);
            model.addFlashAttribute("message", "Successfully added user details");
        }catch (Exception e){
            model.addFlashAttribute("message", "Failed to add user details");
        }
        return "redirect:/" + ResourcePath.USERHANDLING;
    }
}
