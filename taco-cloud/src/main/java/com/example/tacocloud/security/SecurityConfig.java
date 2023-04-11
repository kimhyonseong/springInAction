package com.example.tacocloud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests()
            .requestMatchers("/design","/orders").hasRole("USER")
            .requestMatchers("/","/images/**").permitAll()
            .and().httpBasic();
    return http.build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsManager() {
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    UserDetails user1 = User.builder()
            .username("user1")
            .password(passwordEncoder.encode("password1"))
            .roles("USER").build();

    UserDetails user2 = User.builder()
            .passwordEncoder(passwordEncoder::encode)
            .username("user2")
            .password("password2")
            .roles("USER").build();

    return new InMemoryUserDetailsManager(user1,user2);
  }
}
