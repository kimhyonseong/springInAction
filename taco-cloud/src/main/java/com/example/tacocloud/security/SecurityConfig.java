package com.example.tacocloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
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

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .authorizeHttpRequests()
            .requestMatchers("/design","/orders").hasRole("USER")
            .requestMatchers(PathRequest.toH2Console()).permitAll()  // h2 콘솔
            .requestMatchers("/","/**").permitAll()
            .and()
            .csrf().disable()
            .headers().frameOptions().disable().and()  //iframe 나오게 하기
            .httpBasic();
    return http.build();
  }

//  @Autowired
//  public void ldapConfigure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.ldapAuthentication()
//            .userSearchBase("ou=people")
//            .userSearchFilter("(uid={0})")
//            .groupSearchBase("ou=groups")
//            .groupSearchFilter("member={0}")
//            .contextSource()
////            .root("dc=tacocloud,dc=com")
////            .ldif("classpath:users.ldif")
////            .url("ldap://localhost:8389/dc=tacocloud,dc=com")
//            .and()
//            .passwordCompare()
//            .passwordEncoder(new BCryptPasswordEncoder())
//            .passwordAttribute("userPasscode");
//  }

  /*
  @Bean
  public EmbeddedLdapServerContextSourceFactoryBean contextSourceFactoryBean() {
    EmbeddedLdapServerContextSourceFactoryBean contextSourceFactoryBean =
            EmbeddedLdapServerContextSourceFactoryBean.fromEmbeddedLdapServer();
    contextSourceFactoryBean.setPort(8389);
    return contextSourceFactoryBean;
  }

  @Bean
  AuthenticationManager ldapAuthenticationManager(
          BaseLdapPathContextSource contextSource) {
    LdapBindAuthenticationManagerFactory factory =
            new LdapBindAuthenticationManagerFactory(contextSource);
    factory.setUserDnPatterns("uid={0},ou=people");
    factory.setUserDetailsContextMapper(new PersonContextMapper());
    return factory.createAuthenticationManager();
  }
   */

  @Bean
  public AuthenticationManager userDetailsManager(HttpSecurity security, UserDetailsService userDetailsService) throws Exception{
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    return security.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder)
            .and().build();
  }
}
