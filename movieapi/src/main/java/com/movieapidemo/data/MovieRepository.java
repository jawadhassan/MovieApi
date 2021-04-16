package com.movieapidemo.data;

import org.springframework.data.repository.CrudRepository;

import com.movieapidemo.entity.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long> {

}
