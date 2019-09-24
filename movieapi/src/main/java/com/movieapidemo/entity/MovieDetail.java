package com.movieapidemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="MOVIEDETAIL")
//@JsonIgnoreProperties(value={"movie"},allowSetters=true)
public class MovieDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="budget")
	private int budget;
	
	@Column(name="revenue")
	private int revenue;
	
	@Column(name="run_time")
	private int runtime;

	@Column(name="vote_count")
	private int voteCount;
	

	@OneToOne(mappedBy="movieDetail")
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

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
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


	@Override
	public String toString() {
		return "MovieDetail [id=" + id + ", budget=" + budget + ", revenue=" + revenue + ", runtime=" + runtime
				+ ", voteCount=" + voteCount + ", movie=" + movie + "]";
	}

	
}
