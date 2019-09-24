package com.movieapidemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movieapidemo.dao.MovieDao;
import com.movieapidemo.entity.Movie;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieDao movieDao;
	
	
	@Override
	@Transactional
	public List<Movie> getMovies() {
		return  movieDao.getMovies();
	}

	@Override
	@Transactional
	public void saveMovie(Movie movie) {
		 movieDao.saveMovie(movie);
	}

	@Override
	@Transactional
	public Movie getMovie(int id) {
		return movieDao.getMovie(id);
	}

	@Override
	@Transactional
	public void deleteMovie(int id) {
		movieDao.deleteMovie(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Movie> getMoviesByTagId(int id) {
		
		 List<Movie> movies = movieDao.getMoviesByTagId(id);
		 
		 System.out.println(movies.size());
		 	 
		 return movies;
	}

	
	
}
