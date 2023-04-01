package com.tradeteam.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tradeteam.entities.User;
import com.tradeteam.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User login(String userName, String userPassword) {
        return userRepository.findUserByUserNameAndUserPassword(userName, userPassword);
    }
}
