package com.movieapidemo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.movieapidemo.entity.User;

public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public User getUser(String username) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<User> query = currentSession.createQuery("from user where username:=username");

		query.setParameter("username", username);

		query.setMaxResults(1);

		User user = (User) query.uniqueResult();

		return user;
	}

	@Override
	public void saveUser(User user) {

		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(user);

	}

	@Override
	public void deleteUser(User user) {

		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(user);

	}

	@Override
	public void updateUser(User user) {

		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(user);

	}

	@Override
	public User getUser(int id) {

		Session currentSession = sessionFactory.getCurrentSession();

		User user = currentSession.get(User.class, id);

		return user;
	}

}
