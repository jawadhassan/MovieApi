package com.movieapidemo.dao;

import com.movieapidemo.entity.User;

public interface UserDao {

	public User getUser(String username);

	public User getUser(int id);

	public void saveUser(User user);

	public void deleteUser(User user);

	public void updateUser(User user);

}
