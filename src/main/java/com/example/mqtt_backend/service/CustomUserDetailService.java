package com.example.mqtt_backend.service;

import com.example.mqtt_backend.controller.UserController;
import com.example.mqtt_backend.entity.LoginUsers;
import com.example.mqtt_backend.repository.LoginUsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private LoginUsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUsers user = usersRepository.findByEmail(username);
        if(user == null) {
            logger.warn("Invalid User : {}", username);
            throw new UsernameNotFoundException("User not found");
        }
        logger.info("User : {} found", username);
        return new CustomUserDetail(user);
    }
}
