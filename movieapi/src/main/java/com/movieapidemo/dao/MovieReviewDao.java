package com.movieapidemo.dao;

import java.util.List;

import com.movieapidemo.entity.Movie;
import com.movieapidemo.entity.MovieReview;

public interface MovieReviewDao {

	public List<MovieReview> getReviews(int movie_id);
	
	public void saveMovieReview(MovieReview movieReview,int movie_id);
	
}
