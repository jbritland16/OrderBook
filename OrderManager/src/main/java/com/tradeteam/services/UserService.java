package com.tradeteam.services;

import com.tradeteam.entities.User;

public interface UserService {

    public User createAccount(User user);

    public User login(User user);

    public User updateEmail(User user, String newEmail);

    public User updatePassword(User user, String password);

}
