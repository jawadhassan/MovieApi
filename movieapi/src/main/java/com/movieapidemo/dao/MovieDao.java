package com.movieapidemo.dao;

import java.util.List;

import com.movieapidemo.entity.KeyWord;
import com.movieapidemo.entity.Movie;

public interface MovieDao {

	public List<Movie> getMovies();

	public List<Movie> getMoviesByKeyWordId(int keyWordId);

	public void saveMovie(Movie movie);

	public Movie getMovie(int id);

	public void deleteMovie(int id);

	public Movie getLatest();

	public List<KeyWord> getKeyWords(int id);

}
