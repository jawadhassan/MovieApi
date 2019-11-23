package com.movieapidemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movieapidemo.entity.Movie;
import com.movieapidemo.entity.Tag;

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
		
		/*
		 * Query query =
		 * currentSession.createQuery("delete from Movie where id=:movieid");
		 * 
		 * query.setParameter("movieid", id);
		 * 
		 * query.executeUpdate();
		 */
		
		Movie movie = currentSession.get(Movie.class,id);
		
		currentSession.delete(movie);
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

	@Override
	public Movie getLatest() {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query query = currentSession.createQuery("from Movie mv INNER JOIN FETCH mv.tags INNER JOIN FETCH mv.movieReviews order by mv.releaseDate DESC",Movie.class);
		
		query.setMaxResults(1);
		
		Movie movie = (Movie) query.uniqueResult();
		
		return movie;
	}

	@Override
	public List<Tag> getTags(int id) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		//Query query = currentSession.createQuery("Select tg from Tag tg INNER JOIN tags mvt ON mvt.tag_id = tg.id where mvt.movie_id",Tag.class);
		

		Query query = currentSession.createQuery("Select tg from Tag tg inner join fetch tg.movies mvt where mvt.id =:movieId",Tag.class);
		
		query.setParameter("movieId",id);

		List<Tag> tags = query.getResultList();
		
		//Movie movie = currentSession.get(Movie.class,id);
		
		return tags;
	}

	
	

	
	
}
