package com.movieapidemo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movieapidemo.entity.Movie;
import com.movieapidemo.entity.MovieDetail;

@Repository
public class MovieDetailDaoImpl implements MovieDetailDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void saveMovieDetail(MovieDetail movieDetail, int movie_id) {

		/*
		 * Supplier result = (Supplier) getSession().find(Supplier.class, id);
		 * Hibernate.initialize(result.getIngredients()); return result;
		 */
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Movie> query = currentSession.createQuery("Select mv from Movie mv where mv.id=:movie_id", Movie.class);

		query.setParameter("movie_id", movie_id);

		query.setMaxResults(1);

		Movie movie = (Movie) query.uniqueResult();

		movieDetail.setMovie(movie);

		currentSession.saveOrUpdate(movieDetail);

	}

	@Override
	public void deleteMovieDetail(int id) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query query = currentSession.createQuery("delete from MovieDetail where id=:movieDetailId");

		query.setParameter("movieDetailId", id);

		query.executeUpdate();

	}

	@Override
	public MovieDetail getMovieDetail(int id) {

		Session currentSession = sessionFactory.getCurrentSession();

		MovieDetail movieDetail = currentSession.get(MovieDetail.class, id);

		return movieDetail;
	}

	@Override
	public void rateMovie(int id, int count) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query query = currentSession.createQuery("update MovieDetail set voteCount=:count where movie.id=:movieId");
		query.setParameter("count", count);
		query.setParameter("movieId", id);
	}

}
