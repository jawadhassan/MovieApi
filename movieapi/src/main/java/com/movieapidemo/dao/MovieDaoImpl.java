package com.movieapidemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.movieapidemo.entity.Movie;

@Repository
public class MovieDaoImpl implements MovieDao{

	
	@Autowired 
	private SessionFactory sessionFactory;
	
	
	
	@Override
	public List<Movie> getMovies() {
	
		//Create Session from Session Factory
		
		Session currentSession = sessionFactory.getCurrentSession();
	
		Query<Movie> query = 
				currentSession.createQuery("from Movie order by movie_release_date",Movie.class);
		
		List<Movie> movies = query.getResultList();
	
		return movies;
	}

	@Override
	public void saveMovie(Movie movie) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(movie);
		
	}

	@Override
	public Movie getMovie(int id) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Movie movie = currentSession.get(Movie.class,id);
		
		return movie;
	}

	@Override
	public void deleteMovie(int id) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query query = currentSession.createQuery("delete from Movie where id=:movieid");
		
		query.setParameter("movieid", id);
		
		query.executeUpdate();
	}
    
	@Override
	public List<Movie> getMoviesByTagId(int tagId) {
	
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query query = currentSession.createQuery("Select mv from Movie mv INNER JOIN FETCH mv.tags mvt where mvt.id=:tagId",Movie.class);
		
		                                                          //a FROM Author a JOIN FETCH a.books WHERE a.id = 1
		
		query.setParameter("tagId", tagId);
		
		List<Movie> movies = query.getResultList();
		
		return movies;
	}


	
	
}
