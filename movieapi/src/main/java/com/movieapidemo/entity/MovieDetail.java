package com.movieapidemo.entity;

import java.time.Duration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "MOVIEDETAIL")
//@JsonIgnoreProperties(value={"movie"},allowSetters=true)
public class MovieDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_detail_id")
	private int id;

	@Column(name = "budget", nullable = false)
//	@Min(value = 0, message = "Value cannot be less than zero")
	private int budget;

	@Column(name = "revenue")
//	@Min(value = 0, message = "Value cannot be less than zero")
	private int revenue;

	@Column(name = "run_time", nullable = false)
	private Duration runtime;

	@Column(name = "vote_count")
//	@Min(value = 1, message = "Value cannot be less than 1")
//	@Max(value = 5, message = "Value cannot be greater than 5")
	private int voteCount;

	/*
	 * @JsonIgnoreProperties("movie")
	 * 
	 * @OneToOne(mappedBy = "movieDetail")
	 */

	@JsonIgnoreProperties("movie")
	@OneToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;

	public MovieDetail() {
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public int getRevenue() {
		return revenue;
	}

	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	@JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
	public Duration getRuntime() {
		return runtime;
	}

	public void setRuntime(Duration runtime) {
		this.runtime = runtime;
	}

	@Override
	public String toString() {
		return "MovieDetail [budget=" + budget + ", revenue=" + revenue + ", runtime=" + runtime + ", voteCount="
				+ voteCount + "]";
	}

}
