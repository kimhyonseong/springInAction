package com.example.tacocloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
            .build();
  }

//  @Bean
//  public WebSecurityCustomizer webSecurityCustomizer() {
//    return web -> web.ignoring().requestMatchers("/h2-console/**","/h2-console");
//  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .authorizeHttpRequests()
            .requestMatchers("/design","/orders").hasRole("USER")
            .requestMatchers("/","/images/**").permitAll()
            .requestMatchers(PathRequest.toH2Console()).permitAll()  // h2 콘솔
            .requestMatchers("/h2-console/**").permitAll()  // h2 콘솔
            .and()
            .csrf().disable()
            .headers().frameOptions().disable().and()  //iframe 나오게 하기
            .httpBasic();
    return http.build();
  }

  /*
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
  */
  @Bean
  public UserDetailsManager userDetailsManager(DataSource dataSource) {
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    UserDetails user1 = User.builder()
            .username("user1")
            .password(passwordEncoder.encode("password1"))
            .roles("USER").build();
    JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
    users.createUser(user1);
    return users;
  }
}
