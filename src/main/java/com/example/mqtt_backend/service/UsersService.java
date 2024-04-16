package com.example.mqtt_backend.service;

import com.example.mqtt_backend.constant.ResourcePath;
import com.example.mqtt_backend.entity.LoginUsers;
import com.example.mqtt_backend.repository.LoginUsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    private final LoginUsersRepository loginUsersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersService(LoginUsersRepository loginUsersRepository, PasswordEncoder passwordEncoder) {
        this.loginUsersRepository = loginUsersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isUserExist(String email) {
        return loginUsersRepository.findByEmail(email) != null;
    }

    public void addUser(LoginUsers loginUsers) {
        if(isUserExist(loginUsers.getEmail())) {
            throw new IllegalArgumentException("User already exist");
        }
        LoginUsers user = new LoginUsers(loginUsers.getEmail(), loginUsers.getUsername(), passwordEncoder.encode(loginUsers.getPassword()), loginUsers.getUserRole());
        loginUsersRepository.save(user);
    }

    public List<LoginUsers> getAllUsers() {
        return loginUsersRepository.findAll();
    }

    public void updateUser(Long id ,LoginUsers loginUsers) {
        LoginUsers user = loginUsersRepository.findById(id).orElse(null);
        System.out.println(user);
        if(user == null) {
            throw new IllegalArgumentException("User not found");
        }
        if(loginUsers == null) {
            throw new IllegalArgumentException("User is null");
        }
        if(!loginUsers.getEmail().isEmpty() && !loginUsers.getEmail().equals(user.getEmail())){
            user.setEmail(loginUsers.getEmail());
        }
        if(!loginUsers.getUsername().isEmpty() && !loginUsers.getUsername().equals(user.getUsername())){
            user.setUsername(loginUsers.getUsername());
        }
        if(!(loginUsers.getPassword()==null) && !passwordEncoder.matches(loginUsers.getPassword(), user.getPassword())){
            user.setPassword(passwordEncoder.encode(loginUsers.getPassword()));
        }
        if(!(loginUsers.getUserRole() ==null) && !loginUsers.getUserRole().equals(user.getUserRole())){
            user.setUserRole(loginUsers.getUserRole());
        }
        loginUsersRepository.save(user);
    }

    public void deleteUser(long id) {
        if(loginUsersRepository.existsById(id)) {
            loginUsersRepository.deleteById(id);
        }
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
