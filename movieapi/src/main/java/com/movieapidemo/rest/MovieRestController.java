package com.movieapidemo.rest;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.movieapidemo.data.KeyWordRepository;
import com.movieapidemo.data.MovieDetailRepository;
import com.movieapidemo.data.MovieRepository;
import com.movieapidemo.entity.ApiResponse;
import com.movieapidemo.entity.KeyWord;
import com.movieapidemo.entity.Movie;
import com.movieapidemo.entity.MovieDetail;
import com.movieapidemo.entity.MovieReview;
import com.movieapidemo.exceptions.MovieNotFoundException;
import com.movieapidemo.exceptions.RatingException;

@RestController
public class MovieRestController {
	
	@Autowired
	MovieRepository movieRepo;

	@Autowired
	MovieDetailRepository movieDetailRepo;
	
	@Autowired
	KeyWordRepository keyWordRepo;
	
	
	///////////////////////////////////////
	
	
	// Discover --> page, results, total results, total pages /== /discover/movie
	// Movie Detail -->  object /== movie/{movie id}/
	// Keywords --> id, keywords /== /movie/{movie_id}/keywords
	// Reviews --> id, results, total results, total pages /== movie/{movie_id}/reviews
	// Rate --> object status code, message /== /movie/{movie_id}/rating
	// Delete Rating --> object status code, message  /== /movie/{movie_id}/rating
	// Get latest --> object /== /movie/latest
	// Get Top Rated --> object /== /movie/top_rated
	
	//////////////////////////////////////
	
	
	
	/**
	 * Fetch Movie Details
	 * @param movieId
	 * @return ResponseEntity
	 */
	@GetMapping("/movie/{movieId}")
	public ResponseEntity<?> getMovieDetail(@PathVariable Long movieId) {
		  Optional<MovieDetail> result =  movieDetailRepo.findById(movieId);
		  if(result.isPresent()) {
			  return new ResponseEntity<MovieDetail>(result.get(), HttpStatus.OK);
		  }else {
			  return new ResponseEntity<>(new MovieNotFoundException("Movie not found exception:" + movieId),HttpStatus.NOT_FOUND);
		  }
	}
	
	/**
	 * Fetch Keywords for the Movie
	 * @param movieId
	 * @return ResponseEntity
	 */
	@GetMapping("/movie/{movieId}/keywords")
	public ResponseEntity<?> getKeyWords(@PathVariable Long movieId) {

		Optional<Movie> result =  movieRepo.findById(movieId);
		
		if(result.isPresent()) {
			Movie movie = result.get();
			
			if(movie.getKeyWords() != null) {
				
				return new ResponseEntity<List<KeyWord>>(movie.getKeyWords(),HttpStatus.OK);
			}else {
				return new ResponseEntity<>(new MovieNotFoundException("Movie not found exception:" + movieId),HttpStatus.NOT_FOUND);
			}
			
		}else {
			return new ResponseEntity<>(new MovieNotFoundException("Movie not found exception:" + movieId),HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	/**
	 * Fetch Movie Review of the Movie
	 * @param movieId
	 * @return ResponseEntity
	 */
	@GetMapping("/reviews/{movieId}/reviews")
	public ResponseEntity<?> getReviews(@PathVariable Long movieId) {

		Optional<Movie> result =  movieRepo.findById(movieId);
		
		if(result.isPresent()) {
			Movie movie = result.get();
			
			if(movie.getMovieReviews() != null) {
				return new ResponseEntity<List<MovieReview>>(movie.getMovieReviews(),HttpStatus.OK);
			}else {
				return new ResponseEntity<>(new MovieNotFoundException("Movie not found exception:" + movieId),HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(new MovieNotFoundException("Movie not found exception:" + movieId),HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * Discover Movies List (Pagination is Optional)
	 * @param pageNumber (Optional)
	 * @return ResponseEntity
	 */
	@GetMapping(value = {"/movie/{discover}", "/movie/{discover}/{pageNumber}"})
	public ResponseEntity<?> discoverMovies(@PathVariable Optional<Integer> pageNumber){
		
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		
		PageRequest page = null;
		
		if(pageNumber.isPresent()) {
		
			page = PageRequest.of(
		            pageNumber.get(), 12, Sort.by("createdAt").descending());
		    
		}else {

			page = PageRequest.of(
		            0, 12, Sort.by("createdAt").descending());
		}
		
		movieRepo.findAll(page).forEach(x -> {
			movieList.add(x);
		});
		
		
		if(!movieList.isEmpty()) {
			return new ResponseEntity<List<Movie>>(movieList,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new MovieNotFoundException("Movie not found exception:"),HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	
	@GetMapping("/top_rated")
	public ResponseEntity<?> getTopRated() {

		 
		
		return null;

	}


	
	@PostMapping("/save")
	public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {

		movie.setId(Long.valueOf(0));

//		movieService.saveMovie(movie);

		return new ResponseEntity<Movie>(movie, HttpStatus.OK);

	}

	@PutMapping("/update/{movie_id}")
	public ResponseEntity<Movie> update(@RequestBody Movie movie, @PathVariable int movie_id) {
		// TODO:change it to saveorUpdate
		// TODO:check if there is no movie already will it update?

//		if (movie_id < 0 || (movieService.getMovie(movie_id) == null)) {
//			throw new MovieNotFoundException("Movie not found exception:" + movie_id);
//		}

//		movieService.saveMovie(movie);

		return new ResponseEntity<Movie>(movie, HttpStatus.OK);

	}

	@DeleteMapping("/delete/{movie_id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable int movie_id) {

//		if (movie_id < 0 || (movieService.getMovie(movie_id) == null)) {
//			throw new MovieNotFoundException("Movie not found exception:" + movie_id);
//		}
//
//		movieService.deleteMovie(movie_id);

		ApiResponse apiResponse = new ApiResponse();

		apiResponse.setMessage("Movie Record with ID: " + movie_id + " has been deleted");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);

	}

	/* Movie Detail URLs */

	@PostMapping("/save_movie_detail/{movie_id}")
	public ResponseEntity<ApiResponse> saveMovieDetail(@RequestBody MovieDetail movieDetail,
			@PathVariable int movie_id) {

		// TODO:save movie detail only when movie detail is not present
//		if (movie_id < 0 || (movieService.getMovie(movie_id) == null)) {
//			throw new MovieNotFoundException("Movie not found exception:" + movie_id);
//		}

//		movieDetailService.saveMovieDetail(movieDetail, movie_id);

		ApiResponse apiResponse = new ApiResponse();

		apiResponse.setMessage("Movie Detail Record with Movie ID: " + movie_id + " has been added");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);

	}


	/* Movie Review URLs */

	@PostMapping("/save_review/{movie_id}")
	public ResponseEntity<ApiResponse> saveMovieReview(@PathVariable int movie_id,
			@RequestBody MovieReview movieReview) {

//		if (movie_id < 0 || (movieService.getMovie(movie_id) == null)) {
//			throw new MovieNotFoundException("Movie not found exception:" + movie_id);
//		}

		/*
		 * movieReviewService.saveMovieReview(movieReview, movie_id);
		 */
		ApiResponse apiResponse = new ApiResponse();

		apiResponse.setMessage("Movie Review with Movie ID: " + movie_id + " has been been added");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);

	}



	/* Movie Keywords URLs */


	@PostMapping("save_movie_keywords/{movie_id}")
	public ResponseEntity<ApiResponse> saveMovieKeyWord(@RequestBody KeyWord keyWord, @PathVariable int movie_id) {

//		if (movie_id < 0 || (movieService.getMovie(movie_id) == null)) {
//			throw new MovieNotFoundException("Movie not found exception:" + movie_id);
//		}

//		keyWordService.saveKeyWord(keyWord, movie_id);

		ApiResponse apiResponse = new ApiResponse();

		apiResponse.setMessage("Keyword with Movie ID: " + movie_id + " has been been added");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);

	}

	/* Movie Rating URLs */

	@PostMapping("/rating/{movie_id}")
	public void saveRating(@PathVariable int movie_id, @RequestBody int value) {

//		if (movie_id < 0 || (movieService.getMovie(movie_id) == null)) {
//			throw new MovieNotFoundException("Movie not found exception:" + movie_id);
//		}

		if (value < 0 || value > 5) {
			throw new RatingException("Invalid value, value out of range" + value);
		}

//		movieDetailService.rateMovie(movie_id, value);
	
	}

	@GetMapping("/latest")
	public Movie getLatest() {
//		return movieService.getLatest();
		return null;
	}

	@GetMapping("/recommendations/{movie_id}")
	public Movie getRecommedations(@PathVariable int movie_id) {
		return null;
	}

	@DeleteMapping("/rating/{movie_id}")
	public Movie deleteRating(@PathVariable int movie_id) {
		return null;
	}



	@GetMapping("/all")
	public List<Movie> getAll() {

		ArrayList<Movie> movies = new ArrayList<Movie>();
		
		 movieRepo.findAll().forEach(x -> {
			 movies.add(x);
		 });
		 
		 return movies;

	}
	
	@GetMapping("/print_json_sample")
	public Movie JsonSample() {

		Movie movie = new Movie();

		MovieDetail movieDetail = new MovieDetail();

		KeyWord keyWord = new KeyWord();

		movie.setId(Long.valueOf(0));

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
