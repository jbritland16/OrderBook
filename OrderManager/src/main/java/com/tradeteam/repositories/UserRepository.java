package com.tradeteam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tradeteam.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{



	@Query("FROM User WHERE userName = :userName AND userPassword = :userPassword")
	public User findUserByUserNameAndUserPassword(@Param("userName") String userName,
												  @Param("userPassword") String userPassword);




}
