package com.movieapidemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movieapidemo.dao.TagDao;
import com.movieapidemo.entity.Movie;
import com.movieapidemo.entity.Tag;

@Service
public class TagServiceImpl implements TagService{

	
	@Autowired
	TagDao tagDao;

	@Override
	@Transactional
	public Tag getTag(int id) {
		return tagDao.getTag(id);
	}

	@Override
	@Transactional
	public void deleteTag(int id) {
		tagDao.deleteTag(id);
	}

	@Override
	@Transactional
	public Movie saveTag(Tag tag,int id) {
	   Movie movie  =	tagDao.saveTag(tag, id);
		
	   return movie;
		
	}
	@Override
	public List<Tag> getTags() {
		
		return tagDao.getTags();
	}

	
	
	
	
}
