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
import org.springframework.web.bind.annotation.RestController;

import com.movieapidemo.entity.ApiResponse;
import com.movieapidemo.entity.KeyWord;
import com.movieapidemo.entity.Movie;
import com.movieapidemo.entity.MovieDetail;
import com.movieapidemo.entity.MovieReview;
import com.movieapidemo.exceptions.MovieNotFoundException;
import com.movieapidemo.exceptions.RatingException;
import com.movieapidemo.service.KeyWordService;
import com.movieapidemo.service.MovieDetailService;
import com.movieapidemo.service.MovieReviewService;
import com.movieapidemo.service.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieRestController {

	@Autowired
	MovieService movieService;

	@Autowired
	MovieDetailService movieDetailService;

	@Autowired
	KeyWordService keyWordService;

	@Autowired
	MovieReviewService movieReviewService;

	/* Movie URLs */

	@PostMapping("/save")
	public ResponseEntity<Movie> saveMovie(@Valid @RequestBody Movie movie) {

		movie.setId(0);

		movieService.saveMovie(movie);

		return new ResponseEntity<Movie>(movie, HttpStatus.OK);

	}

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
	public ResponseEntity<ApiResponse> delete(@PathVariable int movie_id) {

		if (movie_id < 0 || (movieService.getMovie(movie_id) == null)) {
			throw new MovieNotFoundException("Movie not found exception:" + movie_id);
		}

		movieService.deleteMovie(movie_id);

		ApiResponse apiResponse = new ApiResponse();

		apiResponse.setMessage("Movie Record with ID: " + movie_id + " has been deleted");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);

	}

	/* Movie Detail URLs */

	@PostMapping("/save_movie_detail/{movie_id}")
	public ResponseEntity<ApiResponse> saveMovieDetail(@Valid @RequestBody MovieDetail movieDetail,
			@PathVariable int movie_id) {

		// TODO:save movie detail only when movie detail is not present
//		if (movie_id < 0 || (movieService.getMovie(movie_id) == null)) {
//			throw new MovieNotFoundException("Movie not found exception:" + movie_id);
//		}

		movieDetailService.saveMovieDetail(movieDetail, movie_id);

		ApiResponse apiResponse = new ApiResponse();

		apiResponse.setMessage("Movie Detail Record with Movie ID: " + movie_id + " has been added");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);

	}

	@GetMapping("/{movie_id}")
	public MovieDetail getMovieDetail(@PathVariable int movie_id) {

		if (movie_id < 0 || (movieService.getMovie(movie_id) == null)) {
			throw new MovieNotFoundException("Movie not found exception:" + movie_id);
		}

		return movieDetailService.getMovieDetail(movie_id);
	}

	/* Movie Review URLs */

	@PostMapping("/save_review/{movie_id}")
	public ResponseEntity<ApiResponse> saveMovieReview(@PathVariable int movie_id,
			@Valid @RequestBody MovieReview movieReview) {

		if (movie_id < 0 || (movieService.getMovie(movie_id) == null)) {
			throw new MovieNotFoundException("Movie not found exception:" + movie_id);
		}

		movieReviewService.saveMovieReview(movieReview, movie_id);

		ApiResponse apiResponse = new ApiResponse();

		apiResponse.setMessage("Movie Review with Movie ID: " + movie_id + " has been been added");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);

	}

	@GetMapping("/reviews/{movie_id}")
	public List<MovieReview> getReviews(@PathVariable int movie_id) {

		if (movie_id < 0 || (movieService.getMovie(movie_id) == null)) {
			throw new MovieNotFoundException("Movie not found exception:" + movie_id);
		}

		List<MovieReview> reviews = movieReviewService.getReviews(movie_id);

		return reviews;
	}

	/* Movie Keywords URLs */

	@GetMapping("/keywords/{movie_id}")
	public List<KeyWord> getKeyWords(@PathVariable int movie_id) {
		// TODO:check if it should be part of keyWord service

		if (movie_id < 0 || (movieService.getMovie(movie_id) == null)) {
			throw new MovieNotFoundException("Movie not found exception:" + movie_id);
		}

		return movieService.getTags(movie_id);
	}

	@PostMapping("save_movie_keywords/{movie_id}")
	public void saveMovieKeyWord(@Valid @RequestBody KeyWord keyWord, @PathVariable int movie_id) {

		if (movie_id < 0 || (movieService.getMovie(movie_id) == null)) {
			throw new MovieNotFoundException("Movie not found exception:" + movie_id);
		}

		keyWordService.saveKeyWord(keyWord, movie_id);
	}

	/* Movie Rating URLs */

	@PostMapping("/rating/{movie_id}")
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

	@GetMapping("/recommendations/{movie_id}")
	public Movie getRecommedations(@PathVariable int movie_id) {
		return null;
	}

	@DeleteMapping("/rating/{movie_id}")
	public Movie deleteRating(@PathVariable int movie_id) {
		return null;
	}

	@GetMapping("/top_rated")
	public Movie getTopRated() {

		return null;

	}

	@GetMapping("/print_json_sample")
	public Movie JsonSample() {

		Movie movie = new Movie();

		MovieDetail movieDetail = new MovieDetail();

		KeyWord keyWord = new KeyWord();

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

		Set<KeyWord> tagSet = new HashSet<KeyWord>();

		keyWord.setKeywordTitle("Action");

		tagSet.add(keyWord);

		keyWord.setKeywordTitle("Comedy");

		tagSet.add(keyWord);

		// movie.setTags(tagSet);

		MovieReview movieReview = new MovieReview();

		movieReview.setAuthor("James");

		movieReview.setContent("Good movie");

		movieReview.setEmail("sdfghjkl");

		movieReview.setMovie(movie);

		HashSet<MovieReview> reviewCollection = new HashSet<MovieReview>();

		reviewCollection.add(movieReview);

		// movie.setMovieReviews(reviewCollection);

		movie.setMovieDetail(movieDetail);

		return movie;
	}

}
