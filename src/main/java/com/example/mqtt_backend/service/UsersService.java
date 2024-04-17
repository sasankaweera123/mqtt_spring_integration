package com.example.mqtt_backend.service;

import com.example.mqtt_backend.constant.ResourcePath;
import com.example.mqtt_backend.entity.LoginUsers;
import com.example.mqtt_backend.repository.LoginUsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    private final Logger logger = LoggerFactory.getLogger(UsersService.class);

    private final LoginUsersRepository loginUsersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersService(LoginUsersRepository loginUsersRepository, PasswordEncoder passwordEncoder) {
        this.loginUsersRepository = loginUsersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Check if user exist
     * @param email The email of the user
     * @return true if user exist, false otherwise
     */
    public boolean isUserExist(String email) {
        return loginUsersRepository.findByEmail(email) != null;
    }

    /**
     * Add user
     * @param loginUsers The user to add
     */
    public void addUser(LoginUsers loginUsers) {
        if(isUserExist(loginUsers.getEmail())) {
            logger.warn("User already exist");
            throw new IllegalArgumentException("User already exist");
        }
        LoginUsers user = new LoginUsers(loginUsers.getEmail(), loginUsers.getUsername(), passwordEncoder.encode(loginUsers.getPassword()), loginUsers.getUserRole());
        loginUsersRepository.save(user);
        logger.info("user saved");
    }

    /**
     * Get all users
     * @return List of all users
     */
    public List<LoginUsers> getAllUsers() {
        return loginUsersRepository.findAll();
    }

    public void updateUser(Long id ,LoginUsers loginUsers) {
        LoginUsers user = loginUsersRepository.findById(id).orElse(null);
        System.out.println(user);
        if(user == null) {
            logger.warn("User not found");
            throw new IllegalArgumentException("User not found");
        }
        if(loginUsers == null) {
            logger.warn("User is null");
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
        logger.info("User updated");
    }

    /**
     * Delete user
     * @param id The id of the user to delete
     */
    public void deleteUser(long id) {
        if(loginUsersRepository.existsById(id)) {
            loginUsersRepository.deleteById(id);
            logger.info("User deleted{}", id);
        }
    }

    /**
     * TODO: Check if function is used or remove
     * User login
     * @param email The email of the user
     * @param password The password of the user
     * @return The login status
     */
    public String UserLogin(String email, String password) {
        LoginUsers user = loginUsersRepository.findByEmail(email);
        if (user != null) {
            boolean isPasswordMatch = passwordEncoder.matches(password, user.getPassword());
            logger.info("Password match : {}", isPasswordMatch);
            if (isPasswordMatch) {
                Optional<LoginUsers> userOptional = loginUsersRepository.findByEmailAndPassword(email, user.getPassword());
                if (userOptional.isPresent()) {
                    logger.info("User found");
                    return ResourcePath.LOGIN_SUCCESS;
                } else {
                    logger.warn("User not found{}", userOptional);
                    return ResourcePath.LOGIN_FAILED_MSG;
                }
            } else {
                logger.warn("Password incorrect");
                return ResourcePath.LOGIN_PASSWORD_INCORRECT;
            }
        } else {
            logger.warn("Email not found");
            return ResourcePath.LOGIN_EMAIL_NOT_FOUND;
        }

    }

}
