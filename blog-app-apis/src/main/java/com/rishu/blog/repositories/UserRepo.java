package com.rishu.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishu.blog.entity.User;

public interface UserRepo extends  JpaRepository<User,Integer>
{
	
	
}