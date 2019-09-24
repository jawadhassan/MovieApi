package com.movieapidemo.service;

import java.util.List;

import com.movieapidemo.entity.Tag;

public interface TagService {

	public Tag getTag(int id);
	
	public void deleteTag(int id);
	
	public void saveTag(Tag tag);

	public List<Tag> getTags();
	
	
}
