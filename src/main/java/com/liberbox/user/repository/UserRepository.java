package com.liberbox.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.liberbox.user.domain.User;

public interface UserRepository extends JpaRepository<User, String> {

	@Query("SELECT u FROM User u WHERE u.id = :userId AND u.active = true")
	Optional<User> findByIdAndActive(String userId);

	@Query("SELECT u FROM User u WHERE u.email = :email AND  u.active = true")
	Optional<User> findByEmail(String email);

}
