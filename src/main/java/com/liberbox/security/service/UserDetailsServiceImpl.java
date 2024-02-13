package com.liberbox.security.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.liberbox.security.domain.UserSS;
import com.liberbox.user.domain.User;
import com.liberbox.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = repository.findByEmail(email);
		if (user.isPresent()) {
			return new UserSS(user.get().getId(), user.get().getEmail(), user.get().getPassword(),
					user.get().getProfiles());
		}
		throw new UsernameNotFoundException(email);
	}

}