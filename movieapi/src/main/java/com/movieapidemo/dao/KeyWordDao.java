package com.movieapidemo.dao;

import java.util.List;

import com.movieapidemo.entity.KeyWord;

public interface KeyWordDao {

	public KeyWord getKeyWord(int id);

	public void deleteKeyWord(int id);

	public void saveKeyWord(KeyWord keyWord, int id);

	public List<KeyWord> getKeyWords();

}
