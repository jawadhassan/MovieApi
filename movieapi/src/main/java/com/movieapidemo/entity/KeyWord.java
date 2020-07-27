package com.movieapidemo.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "KEYWORD")
public class KeyWord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "keyword_id")
	private int id;

	@Column(name = "keyword_title")
	@NotBlank(message = "KeyWord Title cannot be empty")
	@Length(max = 50, message = "The field must be less than 50 characters")
	private String keywordTitle;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinTable(name = "MOVIE_KEYWORD", joinColumns = @JoinColumn(name = "keyword_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
	private Set<Movie> movies;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKeywordTitle() {
		return keywordTitle;
	}

	public void setKeywordTitle(String tagTitle) {
		this.keywordTitle = tagTitle;
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "keyWord [id=" + id + ", tagTitle=" + keywordTitle + "]";
	}

}
