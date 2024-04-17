package com.example.mqtt_backend.beans;

import com.example.mqtt_backend.beans.handler.CustomSuccessHandler;
import com.example.mqtt_backend.constant.ResourcePath;
import com.example.mqtt_backend.enumeration.UserRole;
import com.example.mqtt_backend.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Autowired
    CustomSuccessHandler customSuccessHandler;

    @Autowired
    CustomUserDetailService userDetailService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(a->a
                        .requestMatchers("/"+ResourcePath.DASHBOARD, "/"+ResourcePath.SOUND_BOX).hasAnyAuthority(UserRole.ADMIN.name(),UserRole.BANKUSER.name())
                                .requestMatchers("/"+ResourcePath.USERHANDLING).hasAuthority(UserRole.ADMIN.name())
                                .requestMatchers(
                                "/css/**",
                                "/js/**",
                                "/img/**",
                                "/images/**",
                                "/webjars/**")
                                .permitAll()
                                .anyRequest().authenticated())
                .formLogin(form-> form
                        .loginPage("/"+ResourcePath.LOGIN)
                        .successHandler(customSuccessHandler)
                        .permitAll())
                .logout(form -> form.invalidateHttpSession(true).clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/"+ResourcePath.LOGOUT))
                        .logoutSuccessUrl("/"+ResourcePath.LOGIN).permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }
}
