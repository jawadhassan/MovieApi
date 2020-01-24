package com.movieapidemo.dao;

import com.movieapidemo.entity.MovieDetail;

public interface MovieDetailDao {

	public void saveMovieDetail(MovieDetail movieDetail, int movie_id);

	public void deleteMovieDetail(int id);

	public MovieDetail getMovieDetail(int id);

	public void rateMovie(int id, int count);

}
