package com.example.mqtt_backend.service;

import com.example.mqtt_backend.constant.ResourcePath;
import com.example.mqtt_backend.entity.LoginUsers;
import com.example.mqtt_backend.repository.LoginUsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    private final LoginUsersRepository loginUsersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersService(LoginUsersRepository loginUsersRepository, PasswordEncoder passwordEncoder) {
        this.loginUsersRepository = loginUsersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(LoginUsers loginUsers) {
        LoginUsers user = new LoginUsers(loginUsers.getEmail(), loginUsers.getUsername(), passwordEncoder.encode(loginUsers.getPassword()));
        loginUsersRepository.save(user);
    }

    public LoginUsers getUserByEmail(String email) {
        return loginUsersRepository.findByEmail(email);
    }

    public LoginUsers getUserByEmailAndPassword(String email, String password) {
        return loginUsersRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    public String UserLogin(String email, String password) {
        LoginUsers user = loginUsersRepository.findByEmail(email);
        if (user != null) {
            boolean isPasswordMatch = passwordEncoder.matches(password, user.getPassword());
            if (isPasswordMatch) {
                Optional<LoginUsers> userOptional = loginUsersRepository.findByEmailAndPassword(email, user.getPassword());
                if (userOptional.isPresent()) {
                    return ResourcePath.LOGIN_SUCCESS;
                } else {
                    return ResourcePath.LOGIN_FAILED_MSG;
                }
            } else {
                return ResourcePath.LOGIN_PASSWORD_INCORRECT;
            }
        } else {
            return ResourcePath.LOGIN_EMAIL_NOT_FOUND;
        }

    }
}
