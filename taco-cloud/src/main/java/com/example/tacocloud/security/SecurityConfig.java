package com.example.tacocloud.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
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

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .authorizeHttpRequests()
            .requestMatchers("/design","/orders")
            .access(AuthorityAuthorizationManager.hasRole("USER"))
            //.hasRole("USER")
            .requestMatchers(PathRequest.toH2Console()).permitAll()  // h2 콘솔
            .requestMatchers("/","/**").permitAll()
            .and()
            .headers().frameOptions().disable().and()  //iframe 나오게 하기
            .httpBasic()
            .and()
            .formLogin().loginPage("/login")  // 커스텀 로그인 페이지
            //.loginProcessingUrl("/authenticate")
            .usernameParameter("user")
            .passwordParameter("pwd")
            .defaultSuccessUrl("/design")
            .and()
            .logout()
            .logoutSuccessUrl("/")
            .and()
            .csrf()
            .disable()
    ;
    return http.build();
  }

  @Bean
  public AuthenticationManager userDetailsManager(HttpSecurity security, UserDetailsService userDetailsService) throws Exception{
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    return security.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder)
            .and().build();
  }
}
