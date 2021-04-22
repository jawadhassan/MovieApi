package com.movieapidemo.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.movieapidemo.entity.Movie;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {

	Page<Movie> findAll(Pageable pageable);
	
}
