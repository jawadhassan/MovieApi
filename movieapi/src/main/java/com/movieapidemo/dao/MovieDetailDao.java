package com.movieapidemo.dao;

import com.movieapidemo.entity.MovieDetail;

public interface MovieDetailDao {
	
	public void saveMovieDetail(MovieDetail moviedetail);
	
	public void deleteMovieDetail(int id);
	
   public MovieDetail getMovieDetail (int id);	
	
}
