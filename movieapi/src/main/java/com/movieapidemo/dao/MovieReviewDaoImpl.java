package com.movieapidemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movieapidemo.entity.Movie;
import com.movieapidemo.entity.MovieReview;

@Repository
public class MovieReviewDaoImpl implements MovieReviewDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<MovieReview> getReviews(int movie_id) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query query = currentSession.createQuery("Select mv.movieReviews from Movie mv where mv.id=:movie_id");

		query.setParameter("movie_id", movie_id);

		List<MovieReview> movieReview = query.getResultList();

		return movieReview;
	}

	@Override
	public void saveMovieReview(MovieReview movieReview, int movie_id) {

		Session currentSession = sessionFactory.getCurrentSession();

		Movie movie = currentSession.get(Movie.class, movie_id);

		movieReview.setMovie(movie);

//		movie.addMovieReview(movieReview);

		currentSession.saveOrUpdate(movieReview);

	}

}
