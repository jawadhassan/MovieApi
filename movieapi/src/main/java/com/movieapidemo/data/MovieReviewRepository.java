package com.movieapidemo.data;

import org.springframework.data.repository.CrudRepository;

import com.movieapidemo.entity.MovieReview;

public interface MovieReviewRepository extends CrudRepository<MovieReview, Long> {

}
