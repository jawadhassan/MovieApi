package com.movieapidemo.data;

import org.springframework.data.repository.CrudRepository;

import com.movieapidemo.entity.MovieDetail;

public interface MovieDetailRepository extends CrudRepository<MovieDetail, Long> {

}
