package com.movieapidemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movieapidemo.entity.KeyWord;
import com.movieapidemo.entity.Movie;

@Repository
public class KeyWordDaoImpl implements KeyWordDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public KeyWord getKeyWord(int id) {

		Session currentSession = sessionFactory.getCurrentSession();

		KeyWord keyWord = currentSession.get(KeyWord.class, id);

		return keyWord;
	}

	@Override
	public void deleteKeyWord(int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<KeyWord> query = currentSession.createQuery("delete from keyWord where id:=keyWordId");

		query.setParameter("keyWordId", id);

		query.executeUpdate();
	}

	@Override
	public void saveKeyWord(KeyWord keyWord, int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		Movie movie = currentSession.get(Movie.class, id);

		movie.addkeyWord(keyWord);

		currentSession.saveOrUpdate(keyWord);

	}

	@Override
	public List<KeyWord> getKeyWords() {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<KeyWord> query = currentSession.createQuery("from keyWord", KeyWord.class);

		List<KeyWord> keyWords = query.getResultList();

		return keyWords;
	}

}
