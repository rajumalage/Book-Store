package com.onlinebookstore.service.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlinebookstore.domain.User;
import com.onlinebookstore.domain.security.PasswordResetToken;
import com.onlinebookstore.domain.security.UserRole;
import com.onlinebookstore.repository.PasswordResetTokenRepository;
import com.onlinebookstore.repository.RoleRepository;
import com.onlinebookstore.repository.UserRepository;
import com.onlinebookstore.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Override
	public PasswordResetToken getPasswordResetToken(final String token) {
		return passwordResetTokenRepository.findByToken(token);
	}

	@Override
	public void createPasswordResetTokenForUser(final User user, final String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
		passwordResetTokenRepository.save(myToken);
	}
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	@Transactional
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		User localUser = userRepository.findByUsername(user.getUsername());
		
		if(localUser != null) {
			/*throw new Exception("User {} already exists.");*/
			LOG.info("User {} already exists.", user.getUsername());
		} else {
			for(UserRole ur: userRoles) {
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			localUser = userRepository.save(user);
			
			
		}
		return localUser;
		
	}
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

}
