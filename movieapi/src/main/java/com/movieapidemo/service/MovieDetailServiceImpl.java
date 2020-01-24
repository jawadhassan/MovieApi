package com.movieapidemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movieapidemo.dao.MovieDetailDao;
import com.movieapidemo.entity.MovieDetail;

@Service
public class MovieDetailServiceImpl implements MovieDetailService {

	@Autowired
	MovieDetailDao movieDetailDao;

	@Override
	@Transactional
	public void saveMovieDetail(MovieDetail movieDetail, int movie_id) {

		movieDetailDao.saveMovieDetail(movieDetail, movie_id);

	}

	@Override
	@Transactional
	public MovieDetail getMovieDetail(int id) {

		return movieDetailDao.getMovieDetail(id);

	}

	@Override
	@Transactional
	public void deleteMovieDetail(int id) {

		movieDetailDao.deleteMovieDetail(id);
	}

	@Override
	@Transactional
	public void rateMovie(int id, int count) {
		movieDetailDao.rateMovie(id, count);
	}

}
