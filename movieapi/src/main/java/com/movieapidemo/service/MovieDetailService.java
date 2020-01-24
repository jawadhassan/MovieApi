package com.movieapidemo.service;

import com.movieapidemo.entity.MovieDetail;

public interface MovieDetailService {

	public void saveMovieDetail(MovieDetail movieDetail, int movie_id);

	public MovieDetail getMovieDetail(int id);

	public void deleteMovieDetail(int id);

	public void rateMovie(int id, int count);

}
