package com.movieapidemo.entity;

import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="MOVIE")
@JsonIgnoreProperties(value={"movieDetail","tags"},allowSetters=true)
public class Movie {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="movie_status")
	private String status;
	 
	@Column(name="movie_tag_line")
	private String tagline;
	
	@Column(name="movie_title")
	private String title;
	
	
	@Column(name="movie_release_date")
	private Date releaseDate;

	@OneToOne(cascade=CascadeType.ALL) 
    @JoinColumn(name="movie_detail_id")   
	private MovieDetail movieDetail;
	
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="MOVIETAG",
			joinColumns=@JoinColumn(name="movie_id"),
			inverseJoinColumns=@JoinColumn(name="tag_id")
	)
	private Set<Tag> tags = new HashSet<Tag>();

	public Movie() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public MovieDetail getMovieDetail() {
		return movieDetail;
	}

	public void setMovieDetail(MovieDetail movieDetail) {
		this.movieDetail = movieDetail;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", status=" + status + ", tagline=" + tagline + ", title=" + title + ", releaseDate="
				+ releaseDate + ", movieDetail=" + movieDetail + "]";
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}


	
	

}
