package com.onlinebookstore;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.onlinebookstore.domain.User;
import com.onlinebookstore.domain.security.Role;
import com.onlinebookstore.domain.security.UserRole;
import com.onlinebookstore.service.UserService;
import com.onlinebookstore.utility.SecurityUtility;

@SpringBootApplication
public class OnlinebookstoreApplication implements CommandLineRunner{
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(OnlinebookstoreApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setFirstName("Rajendra");
		user1.setLastName("Prasad");
		user1.setUsername("Raj");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
		user1.setEmail("rajendraprasad.malage@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1 = new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1, userRoles);
		
	}
}

