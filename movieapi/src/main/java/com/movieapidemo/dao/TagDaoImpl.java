package com.movieapidemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movieapidemo.entity.Movie;
import com.movieapidemo.entity.Tag;

@Repository
public class TagDaoImpl implements TagDao {

	
	@Autowired
	SessionFactory sessionFactory;
	
	
	@Override
	public Tag getTag(int id) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Tag tag = currentSession.get(Tag.class, id);
		
		return tag;
	}

	@Override
	public void deleteTag(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Tag> query = 
				currentSession.createQuery("delete from Tag where id:=tagId");
		
				query.setParameter("tagId", id);
						
				query.executeUpdate();
	}

	@Override
	public Movie saveTag(Tag tag,int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Movie movie = currentSession.get(Movie.class, id);
		
		movie.addTag(tag);
		
		currentSession.saveOrUpdate(tag);
		
		return movie;
		
	}

	@Override
	public List<Tag> getTags() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Tag> query =
				currentSession.createQuery("from Tag",Tag.class);
		
		List<Tag>tags = query.getResultList();
		
		return tags;
	}

	
	
	
}
