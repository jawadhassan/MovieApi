package com.movieapidemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.movieapidemo.entity.KeyWord;
import com.movieapidemo.entity.Movie;

@Service
public interface MovieService {

	public List<Movie> getMovies();

	public void saveMovie(Movie movie);

	public Movie getMovie(int id);

	public List<Movie> getMoviesByKeyWordId(int id);

	public void deleteMovie(int id);

	public Movie getLatest();

	public List<KeyWord> getTags(int id);

}
