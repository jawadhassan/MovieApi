package com.movieapidemo.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "MOVIEREVIEW")
public class MovieReview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_review_id")
	private int id;

	@Column(name = "author")
//	@NotBlank(message="Value cannot be empty")
	@Length(max = 50, message = "The field must be less than 50 characters")
	private String author;

	@Column(name = "content")
//	@NotBlank(message="Value cannot be empty")
	@Length(max = 300, message = "The field must be less than 300 characters")
	private String content;

	@Column(name = "url")
//	@Email(message="Email address cannot be empty")
	private String email;

	@CreationTimestamp
	private Date createDate;

	@UpdateTimestamp
	private Date updateDate;

	public int getId() {
		return id;
	}

	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "movie_id")
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

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "MovieReview [author=" + author + ", content=" + content + ", email=" + email + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "]";
	}

}
