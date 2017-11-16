package com.onlinebookstore.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.onlinebookstore.domain.User;
import com.onlinebookstore.domain.security.PasswordResetToken;
import com.onlinebookstore.domain.security.UserRole;

@Service
public interface UserService {
PasswordResetToken getPasswordResetToken(final String token);
	
	void createPasswordResetTokenForUser(final User user, final String token);
	
    User findByUsername(String username);
	
	User findByEmail(String email);
	
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	User save(User user);
}
