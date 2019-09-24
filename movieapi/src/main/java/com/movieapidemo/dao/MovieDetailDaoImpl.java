package com.movieapidemo.dao;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movieapidemo.entity.MovieDetail;

@Repository
public class MovieDetailDaoImpl implements MovieDetailDao {

	
	@Autowired 
	SessionFactory sessionFactory;
		

	@Override
	public void saveMovieDetail(MovieDetail moviedetail) {
	
		Session CurrentSession = sessionFactory.getCurrentSession();
		
		CurrentSession.saveOrUpdate(moviedetail);
		
	}

	@Override
	public void deleteMovieDetail(int id) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query query = currentSession.createQuery("delete from MovieDetail where id=:movieDetailId");
		
		query.setParameter("movieDetailId",id);
		
		query.executeUpdate();
		
	}

	@Override
	public MovieDetail getMovieDetail(int id) {
	
		Session currentSession = sessionFactory.getCurrentSession();
		
		
		MovieDetail movieDetail = currentSession.get(MovieDetail.class, id);
		

		return movieDetail;
	}

	
	
	
}
