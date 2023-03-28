package com.tradeteam.services;

import com.tradeteam.entities.User;

public interface UserService {

	public User createUser(User user);

	public User login(String userName, String userPassword) ;
}
