package com.rishu.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishu.blog.entity.User;

public interface UserRepo extends  JpaRepository<User,Integer>
{
	Optional<User> findByEmailAndPassword(String email,String password);
	Optional<User> findByEmail(String email);

	
}
