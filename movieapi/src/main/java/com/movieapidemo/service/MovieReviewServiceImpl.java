package com.movieapidemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movieapidemo.dao.MovieReviewDao;
import com.movieapidemo.entity.MovieReview;

@Service
public class MovieReviewServiceImpl implements MovieReviewService{

	
	@Autowired
	MovieReviewDao movieReviewDao;
	
	
	@Override
	@Transactional
	public List<MovieReview> getReviews(int movie_id){
		return movieReviewDao.getReviews(movie_id);
	}
	
	@Override
	@Transactional
	public void saveMovieReview(MovieReview movieReview,int movie_id) {
		movieReviewDao.saveMovieReview(movieReview, movie_id);
	}
}
