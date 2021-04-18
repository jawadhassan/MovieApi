package com.movieapidemo.data;

import org.springframework.data.repository.CrudRepository;

import com.movieapidemo.entity.KeyWord;

public interface KeyWordRepository extends CrudRepository<KeyWord, Long> {

}
