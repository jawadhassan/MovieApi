package com.movieapidemo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="MOVIEREVIEW")
public class MovieReview {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="movie_review_id")
	private int id;
	
	@Column(name="author")
	private String author;
	
	@Column(name="content")
	private String content;
	
	@Column(name="url")
	private String url;

	public int getId() {
		return id;
	}

	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="movie_id")
	private Movie movie;

	
	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	@Override
	public String toString() {
		return "MovieReview [id=" + id + ", author=" + author + ", content=" + content + ", url=" + url + "]";
	}
	
  	
	
	
}
