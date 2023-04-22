package com.ivona.datafood.configuration;

import com.ivona.datafood.exception.UserNotFoundException;
import com.ivona.datafood.user.User;
import com.ivona.datafood.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username).orElse(null);
    if (user == null) {
      throw new UserNotFoundException("No user found with username "+username);
    }
    return UserDetailsImpl.build(user);
  }
}
