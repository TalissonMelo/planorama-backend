package com.liberbox.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liberbox.user.domain.User;

public interface UserRepository extends JpaRepository<User, String> {

}
