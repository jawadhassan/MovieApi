package com.movieapidemo.dao;

import java.util.List;

import com.movieapidemo.entity.Tag;

public interface TagDao {
	
	public Tag getTag(int id);
	
	public void deleteTag(int id);
	
	public void saveTag(Tag tag);
	
	public List<Tag> getTags();

}
