package com.tradeteam.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter @AllArgsConstructor @RequiredArgsConstructor @NoArgsConstructor @ToString @EqualsAndHashCode
public class User {
    @Id //indicates ID
    @GeneratedValue(strategy= GenerationType.AUTO) // Auto generates user ID
	@Setter
	private int userId;
	@NonNull private String userName;
	@NonNull private String userPassword;
	@NonNull private String userEmail;

	public static User login(String userName, String userPassword) {
		return null;
	}

}
