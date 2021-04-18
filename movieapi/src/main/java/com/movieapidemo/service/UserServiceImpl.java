package com.movieapidemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movieapidemo.dao.UserDao;
import com.movieapidemo.entity.User;


@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	UserDao userDao;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	@Transactional
	public void save(User user) {

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		User newUser = user;

		userDao.saveUser(newUser);		
	}

	
	
}
