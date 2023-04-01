package com.tradeteam.security;

import com.tradeteam.entities.User;
import com.tradeteam.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderManagerUserDetailsServiceImpl implements OrderManagerUserDetailsService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(), new ArrayList<>());
    }

    @Override
    public User saveUser(User user) {
        String password = user.getUserPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setUserPassword(encodedPassword);
        user = userRepository.save(user);
        return user;
    }
}
