package com.movieapidemo.rest;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movieapidemo.entity.Movie;
import com.movieapidemo.entity.MovieDetail;
import com.movieapidemo.entity.MovieReview;
import com.movieapidemo.entity.Tag;
import com.movieapidemo.exceptions.MovieNotFoundException;
import com.movieapidemo.exceptions.RatingException;
import com.movieapidemo.service.MovieDetailService;
import com.movieapidemo.service.MovieReviewService;
import com.movieapidemo.service.MovieService;
import com.movieapidemo.service.TagService;

@RestController
@RequestMapping("/movie")
public class MovieRestController {

	@Autowired
	MovieService movieService;

	@Autowired
	MovieDetailService movieDetailService;

	@Autowired
	TagService tagService;

	@Autowired
	MovieReviewService movieReviewService;

	/*
	 * @GetMapping("/movies") public List<Movie> getMovies(){
	 * 
	 * return movieService.getMovies(); }
	 */

	@PostMapping("/save")
	public ResponseEntity<Movie> saveMovie(@Valid @RequestBody Movie movie) {

		movie.setId(0);

		movieService.saveMovie(movie);

		return new ResponseEntity<Movie>(movie, HttpStatus.OK);

	}

	/*
	 * @GetMapping("/{movie_id}") public Movie getMovie(@PathVariable int movie_id)
	 * {
	 * 
	 * if(movie_id >= movieService.getMovies().size() || movie_id< 0) { throw new
	 * MovieNotFoundException("Movie not found exception:"+movie_id); }
	 * 
	 * return movieService.getMovie(movie_id);
	 * 
	 * }
	 */

	@PutMapping("/update/{movie_id}")
	public ResponseEntity<Movie> update(@Valid @RequestBody Movie movie, @PathVariable int movie_id) {
		// TODO:change it to saveorUpdate
		// TODO:check if there is no movie already will it update?

		if (movie_id < 0 || (movieService.getMovie(movie_id) == null)) {
			throw new MovieNotFoundException("Movie not found exception:" + movie_id);
		}

		movieService.saveMovie(movie);

		return new ResponseEntity<Movie>(movie, HttpStatus.OK);

	}

	@DeleteMapping("/delete/{movie_id}")
	public String delete(@PathVariable int movie_id) {

		if (movie_id < 0 || (movieService.getMovie(movie_id) == null)) {
			throw new MovieNotFoundException("Movie not found exception:" + movie_id);
		}

		movieService.deleteMovie(movie_id);

		return ("Movie with" + movie_id + "has been deleted");

	}

	@GetMapping("/{movie_id}/keywords")
	public List<Tag> getKeyWords(@PathVariable int movie_id) {
		// TODO:check if it should be part of tag service

		if (movie_id < 0 || (movieService.getMovie(movie_id) == null)) {
			throw new MovieNotFoundException("Movie not found exception:" + movie_id);
		}

		return movieService.getTags(movie_id);
	}

	@GetMapping("/{movie_id}/recommendations")
	public Movie getRecommedations(@PathVariable int movie_id) {
		return null;
	}

	@GetMapping("/{movie_id}/reviews")
	public List<MovieReview> getReviews(@PathVariable int movie_id) {

		if (movie_id < 0 || (movieService.getMovie(movie_id) == null)) {
			throw new MovieNotFoundException("Movie not found exception:" + movie_id);
		}

		return movieReviewService.getReviews(movie_id);
	}

	@PostMapping("/{movie_id}/save_review")
	public void saveMovieReview(@PathVariable int movie_id, @Valid @RequestBody MovieReview movieReview) {

		if (movie_id < 0 || (movieService.getMovie(movie_id) == null)) {
			throw new MovieNotFoundException("Movie not found exception:" + movie_id);
		}

		movieReviewService.saveMovieReview(movieReview, movie_id);
	}

	@DeleteMapping("/{movie_id}/rating")
	public Movie deleteRating(@PathVariable int movie_id) {
		return null;
	}

	@PostMapping("/{movie_id}/rating")
	public void saveRating(@PathVariable int movie_id, @RequestBody int value) {

		if (movie_id < 0 || (movieService.getMovie(movie_id) == null)) {
			throw new MovieNotFoundException("Movie not found exception:" + movie_id);
		}

		if (value < 0 || value > 5) {
			throw new RatingException("Invalid value, value out of range" + value);
		}

		movieDetailService.rateMovie(movie_id, value);
	}

	@GetMapping("/latest")
	public Movie getLatest() {

		return movieService.getLatest();

	}

	@GetMapping("/top_rated")
	public Movie getTopRated() {

		return null;

	}

	@GetMapping("/{movie_id}")
	public MovieDetail getMovieDetail(@PathVariable int movie_id) {

		if (movie_id < 0 || (movieService.getMovie(movie_id) == null)) {
			throw new MovieNotFoundException("Movie not found exception:" + movie_id);
		}

		return movieDetailService.getMovieDetail(movie_id);
	}

	/*
	 * @GetMapping("/get_movies_by_keyword/{tagId}") public List<Movie>
	 * getMoviesByKeyword(@PathVariable int tagId){ return
	 * movieService.getMoviesByTagId(tagId); }
	 */

//	@PutMapping("/update_movie_detail")
//	public MovieDetail updateMovieDetail(@Valid @RequestBody MovieDetail movieDetail) {
//
//		movieDetailService.saveMovieDetail(movieDetail);
//
//		return movieDetail;
//
//	}

	@PostMapping("/save_movie_detail/{movie_id}")
	@ResponseBody
	public String saveMovieDetail(@Valid @RequestBody MovieDetail movieDetail, @PathVariable int movie_id) {

		movieDetailService.saveMovieDetail(movieDetail, movie_id);

		return ("Movie Detail with movie id:" + movie_id + "has been added");

	}

	@PutMapping("{movie_id}/save_movie_keywords")
	public Movie saveMovieTag(@Valid @RequestBody Tag tag, @PathVariable int movie_id) {

		if (movie_id < 0 || (movieService.getMovie(movie_id) == null)) {
			throw new MovieNotFoundException("Movie not found exception:" + movie_id);
		}

		return tagService.saveTag(tag, movie_id);
	}

	@GetMapping("/print_json_sample")
	public Movie JsonSample() {

		Movie movie = new Movie();

		MovieDetail movieDetail = new MovieDetail();

		Tag tag = new Tag();

		movie.setId(0);

		movie.setReleaseDate(new Date());

		movie.setStatus("Released");

		movie.setTagline("Check it out");

		movie.setTitle("Check Mate");

		movieDetail.setId(0);

		movieDetail.setBudget(10000);

		movieDetail.setRevenue(100);

		LocalTime end = LocalTime.parse("03:00:00");

		LocalTime start = LocalTime.parse("00:00:00");

		Duration duration = Duration.between(start, end);

		movieDetail.setRuntime(duration);

		movieDetail.setVoteCount(0);

		movieDetail.setMovie(movie);

		movie.setMovieDetail(movieDetail);

		Set<Tag> tagSet = new HashSet<Tag>();

		tag.setTagTitle("Action");

		tagSet.add(tag);

		tag.setTagTitle("Comedy");

		tagSet.add(tag);

		movie.setTags(tagSet);

		MovieReview movieReview = new MovieReview();

		movieReview.setAuthor("James");

		movieReview.setContent("Good movie");

		movieReview.setEmail("sdfghjkl");

		movieReview.setMovie(movie);

		HashSet<MovieReview> reviewCollection = new HashSet<MovieReview>();

		reviewCollection.add(movieReview);

		movie.setMovieReviews(reviewCollection);

		movie.setMovieDetail(movieDetail);

		return movie;
	}

}
