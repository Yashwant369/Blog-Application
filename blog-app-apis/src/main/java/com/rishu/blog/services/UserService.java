package com.rishu.blog.services;

import java.util.List;

import com.rishu.blog.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
	UserDto registerNewUser(UserDto user);
	
	
	
	
	
	

}
