package com.movieapidemo.dao;

import java.util.List;
import java.util.Set;

import com.movieapidemo.entity.Movie;
import com.movieapidemo.entity.Tag;

public interface MovieDao {

	public List<Movie> getMovies();
	
	
	public List<Movie> getMoviesByTagId(int tagId);
	
	public void saveMovie(Movie movie);
	
	public Movie getMovie(int id);
	
	public void deleteMovie(int id);
	
	public Movie getLatest();
	
	public List<Tag> getTags(int id);
	
}
