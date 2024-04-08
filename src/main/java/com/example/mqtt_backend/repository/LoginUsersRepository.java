package com.example.mqtt_backend.repository;

import com.example.mqtt_backend.entity.LoginUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface LoginUsersRepository extends JpaRepository<LoginUsers, Long> {
    Optional<LoginUsers> findByEmailAndPassword(String email, String password);
    LoginUsers findByEmail(String email);
}
