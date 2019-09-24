package com.movieapidemo.service;

import com.movieapidemo.entity.MovieDetail;

public interface MovieDetailService {

	public void saveMovieDetail(MovieDetail movieDetail);
	
	public MovieDetail getMovieDetail(int id);
	
	public void deleteMovieDetail(int id);
		
}
