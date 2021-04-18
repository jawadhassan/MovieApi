package com.movieapidemo.service;

import org.springframework.stereotype.Service;

import com.movieapidemo.entity.User;

@Service
public interface UserService {
	
	public void save(User user);

}
