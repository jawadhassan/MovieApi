package com.movieapidemo.rest;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieapidemo.entity.Movie;
import com.movieapidemo.entity.MovieDetail;
import com.movieapidemo.entity.MovieReview;
import com.movieapidemo.entity.Tag;
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
	public Movie saveMovie(@RequestBody Movie movie) {

		movie.setId(0);

		movieService.saveMovie(movie);
		
		return movie;
		
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
	
	
	
	@PutMapping("/update")
	public Movie update(@RequestBody Movie movie) {
		//TODO:change it to saveorUpdate
		//TODO:check if there is no movie already will it update?
		movieService.saveMovie(movie);
		
		return movie;
		
	}
	
	
	@DeleteMapping("/delete/{movie_id}")
	public String delete(@PathVariable int movie_id) {
	
		movieService.deleteMovie(movie_id);
		
		return ("Movie with" + movie_id + "has been deleted");
		
	}
	
	@GetMapping("/{movie_id}/keywords")																					
	public List<Tag> getKeyWords(@PathVariable int movie_id) {
		//TODO:check if it should be part of tag service
		return movieService.getTags(movie_id);
	}
	
	
	@GetMapping("/{movie_id}/recommendations")																					
	public Movie getRecommedations(@PathVariable int movie_id) {
		return null;
	}
	
	
	@GetMapping("/{movie_id}/reviews")																					
	public List<MovieReview> getReviews(@PathVariable int movie_id) {
		return movieReviewService.getReviews(movie_id);
	}

	
	@PutMapping("/{movie_id}/save_review")																					
	public void saveMovieReview(@PathVariable int movie_id,@RequestBody MovieReview movieReview) {
		movieReviewService.saveMovieReview(movieReview,movie_id);
	}

	
	
	@DeleteMapping("/{movie_id}/rating")
	public Movie deleteRating(@PathVariable int movie_id) {
		return null;
	} 
	
	@PostMapping("/{movie_id}/rating")
	public void saveRating(@PathVariable int movie_id,@RequestBody int value) {
		
		movieDetailService.rateMovie(movie_id, value);
	}
	
	
	@GetMapping("/latest") 
	public Movie getLatest(){
	  	  
	 return movieService.getLatest();
	  
	 }
	
	@GetMapping("/top_rated") 
	public Movie getTopRated(){
	  	  
	 return null;
	  
	 }
	
	
	@GetMapping("/{movie_id}")
	public MovieDetail getMovieDetail(@PathVariable int movie_id) {
		
		return movieDetailService.getMovieDetail(movie_id);
	}
	
	

	
	/*
	 * @GetMapping("/get_movies_by_keyword/{tagId}") public List<Movie>
	 * getMoviesByKeyword(@PathVariable int tagId){ return
	 * movieService.getMoviesByTagId(tagId); }
	 */

	
	@PutMapping("/update_movie_detail")
	public MovieDetail updateMovieDetail(@RequestBody MovieDetail movieDetail){
	
		movieDetailService.saveMovieDetail(movieDetail);
		
		return movieDetail;
		
	}
	
	
	@PutMapping("{movie_id}/save_movie_keywords")
	public Movie saveMovieTag(@RequestBody Tag tag,@PathVariable int movie_id){
		return tagService.saveTag(tag, movie_id);
	}			
	
	
	@GetMapping("/print_json_sample")
	public MovieReview JsonSample(){
		
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
		
		movieDetail.setRuntime(20);
		
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
		
		movieReview.setUrl("sdfghjkl");
		
		movieReview.setMovie(movie);
		
		
		
		
		return movieReview;
	}
	
}
