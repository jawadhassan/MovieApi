package com.movieapidemo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="TAG")
@JsonIgnoreProperties(value={"movies"},allowSetters=true)
public class Tag {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="tag_title")
	private String tagTitle;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="MOVIETAG",
			joinColumns=@JoinColumn(name="tag_id"),
			inverseJoinColumns=@JoinColumn(name="movie_id")
	)
	private Set<Movie> movies = new HashSet<Movie>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTagTitle() {
		return tagTitle;
	}
	
	public void setTagTitle(String tagTitle) {
		this.tagTitle = tagTitle;
	}
	
	@Override
	public String toString() {
		return "Tag [id=" + id + ", tagTitle=" + tagTitle + "]";
	}
	
	public Set<Movie> getMovies() {
		return movies;
	}
	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}
	
	
	
	
	
	
	
	
}
