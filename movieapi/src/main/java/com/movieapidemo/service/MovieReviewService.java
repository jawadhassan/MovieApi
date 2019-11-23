package com.movieapidemo.service;

import java.util.List;

import com.movieapidemo.entity.MovieReview;

public interface MovieReviewService {

	public List<MovieReview> getReviews(int movie_id);
	
	public void saveMovieReview(MovieReview movieReview,int movie_id);
	
}
