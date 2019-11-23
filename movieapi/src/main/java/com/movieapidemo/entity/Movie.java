package com.movieapidemo.entity;

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "MOVIE")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_id")
	private int id;

	@Column(name = "movie_status")
	private String status;

	@Column(name = "movie_tag_line")
	private String tagline;

	@Column(name = "movie_title")
	private String title;

	@Column(name = "movie_release_date")
	private Date releaseDate;

	@JsonIgnoreProperties("movie")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "movie_detail_id")
	private MovieDetail movieDetail;

	@JsonIgnoreProperties("movies")
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name = "MOVIE_TAG", 
		joinColumns = @JoinColumn(name = "movie_id"), 
		inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private Set<Tag> tags;

	
	
	  @JsonIgnoreProperties("movie")
	  @OneToMany(fetch=FetchType.LAZY, mappedBy="movie",
	  cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}) 
	  private Set<MovieReview> movieReviews;
	 
	
	
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

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}



	
	public void addTag(Tag tag) {
		if(tags == null) {
			tags = new HashSet<Tag>(); 
		}
		
		tags.add(tag);
	}
	
	
	
	  public void addMovieReview(MovieReview movieReview) { 
		  if(movieReview == null){ 
			
			  movieReviews = new HashSet<MovieReview>(); 
		  
		  }
	  
	  movieReviews.add(movieReview);
	  
	  movieReview.setMovie(this);
	  
	  
	  }
	  
	  
	  
	  public Set<MovieReview> getMovieReviews() { return movieReviews; }
	  
	  public void setMovieReviews(Set<MovieReview> movieReviews) {
	  this.movieReviews = movieReviews; }
	 	
	@Override
	public String toString() {
		return "Movie [id=" + id + ", status=" + status + ", tagline=" + tagline + ", title=" + title + ", releaseDate="
				+ releaseDate + "]";
	}

}
