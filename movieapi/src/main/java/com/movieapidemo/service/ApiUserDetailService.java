package com.movieapidemo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movieapidemo.dao.UserDao;
import com.movieapidemo.entity.User;

@Service
public class ApiUserDetailService implements UserDetailsService {

	@Autowired
	UserDao userDao;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDao.findUserByName(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found with name:" + username);
		}

		// return new User(user.getUsername(), user.getPassword(), new ArrayList<>());

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	
//	@Transactional
//	public void save(User user) {
//
//		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//
//		User newUser = user;
//
//		userDao.saveUser(newUser);
//
//	}
	
	

}
