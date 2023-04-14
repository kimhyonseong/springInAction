package com.example.tacocloud.service;

import com.example.tacocloud.data.UserRepository;
import com.example.tacocloud.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRepositoryUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

//  @Autowired
//  public UserRepositoryUserDetailsService(UserRepository userRepository) {
//    this.userRepository = userRepository;
//  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Users user = userRepository.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("User '"+username+"' not found");
    }
    return user;
  }
}
