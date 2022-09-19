package com.csti.challengebackend.infrastructure.security;

import com.csti.challengebackend.infrastructure.exception.custom.UserNotFoundException;
import com.csti.challengebackend.persistence.entities.User;
import com.csti.challengebackend.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {

  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) {
    User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl.build(user);
  }

}
