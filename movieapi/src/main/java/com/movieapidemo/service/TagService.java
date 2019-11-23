package com.movieapidemo.service;

import java.util.List;

import com.movieapidemo.entity.Movie;
import com.movieapidemo.entity.Tag;

public interface TagService {

	public Tag getTag(int id);
	
	public void deleteTag(int id);
	
	public Movie saveTag(Tag tag,int id);

	public List<Tag> getTags();
	
	
}
