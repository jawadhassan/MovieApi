package com.movieapidemo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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

@Entity
@Table(name = "MOVIE")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_id")
	private Long id;

	@Column(name = "movie_status")
//	@NotBlank(message = "Status cannot be empty")
	private String status;

	@Column(name = "movie_tag_line")
//	@Length(max = 100, message = "The field must be less than 100 characters")
	private String tagline;

	@Column(name = "movie_title")
//	@NotBlank(message = "Title cannot be empty")
//	@Length(max = 50, message = "The field must be less than 50 characters")
	private String title;

	@Column(name = "movie_release_date")
	private Date releaseDate;

	@JsonIgnoreProperties("movie")
	@OneToOne(mappedBy = "movie", cascade = CascadeType.ALL)
	private MovieDetail movieDetail;

	@JsonIgnoreProperties("movies")
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinTable(name = "MOVIE_KEYWORD", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "keyword_id"))
	private Set<KeyWord> keyWords;

	@JsonIgnoreProperties("movie")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "movie", cascade = { CascadeType.ALL })
	private List<MovieReview> movieReviews;

	public Movie() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Set<KeyWord> getkeyWords() {
		return keyWords;
	}

	public void setKeyWords(Set<KeyWord> keyWords) {
		this.keyWords = keyWords;
	}

	public void addkeyWord(KeyWord keyWord) {
		if (keyWords == null) {
			keyWords = new HashSet<KeyWord>();
		}

		keyWords.add(keyWord);
	}

	public void addMovieReview(MovieReview movieReview) {
		if (movieReview == null) {

			movieReviews = new ArrayList<MovieReview>();

		}

		movieReviews.add(movieReview);

		movieReview.setMovie(this);

	}

	public List<MovieReview> getMovieReviews() {
		return movieReviews;
	}

	public void setMovieReviews(List<MovieReview> movieReviews) {
		this.movieReviews = movieReviews;
	}

	@Override
	public String toString() {
		return "Movie [status=" + status + ", tagline=" + tagline + ", title=" + title + ", releaseDate=" + releaseDate
				+ ", movieDetail=" + movieDetail + ", keyWords=" + keyWords + "]";
	}
}
