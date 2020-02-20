package com.movieapidemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movieapidemo.dao.KeyWordDao;
import com.movieapidemo.entity.KeyWord;

@Service
public class KeyWordServiceImpl implements KeyWordService {

	@Autowired
	KeyWordDao keyWordDao;

	@Override
	@Transactional
	public KeyWord getKeyWord(int id) {
		return keyWordDao.getKeyWord(id);
	}

	@Override
	@Transactional
	public void deleteKeyWord(int id) {
		keyWordDao.deleteKeyWord(id);
	}

	@Override
	@Transactional
	public void saveKeyWord(KeyWord keyWord, int id) {
		keyWordDao.saveKeyWord(keyWord, id);

	}

	@Override
	public List<KeyWord> getKeyWords() {

		return keyWordDao.getKeyWords();
	}

}
