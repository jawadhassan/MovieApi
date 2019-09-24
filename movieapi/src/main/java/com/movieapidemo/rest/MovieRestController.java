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
import com.movieapidemo.entity.Tag;
import com.movieapidemo.exceptions.MovieNotFoundException;
import com.movieapidemo.service.MovieDetailService;
import com.movieapidemo.service.MovieService;

@RestController
@RequestMapping("/discover")
public class MovieRestController {
	
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	MovieDetailService movieDetailService;
	
	
	@GetMapping("/movies")
	public List<Movie> getMovies(){
		
		return movieService.getMovies();
	}
	
	@PostMapping("/save_movie")
	public Movie saveMovie(@RequestBody Movie movie) {
		
		movie.setId(0);
		
		movie.getMovieDetail().setId(0);
		
		movieService.saveMovie(movie);
		
		return movie;
		
	}
	
	
	@GetMapping("/find_movie/{id}")
	public Movie getMovie(@PathVariable int id) {
		
		if(id >= movieService.getMovies().size() || id< 0) {
			throw new MovieNotFoundException("Movie not found exception:"+id);
		}
				
		return 	movieService.getMovie(id);

	}
	
	
	
	@PutMapping("/update_movie")
	public Movie update(@RequestBody Movie movie) {

		movieService.saveMovie(movie);
		
		return movie;
		
	}
	
	
	@DeleteMapping("/delete_movie/{id}")
	public String delete(@PathVariable int id) {
	
		movieService.deleteMovie(id);
		
		return ("Movie with" + id + "has been deleted");
		
	}
	
	@GetMapping("/get_movie_detail/{id}")
	public MovieDetail getMovieDetail(@PathVariable int id) {
		
		return movieDetailService.getMovieDetail(id);
	}
	
	@GetMapping("/get_movies_by_keyword/{tagId}")
	public List<Movie> getMoviesByKeyword(@PathVariable int tagId){
		return movieService.getMoviesByTagId(tagId);
	}

	
	@PutMapping("/update_movie_detail")
	public MovieDetail updateMovieDetail(@RequestBody MovieDetail movieDetail){
	
		movieDetailService.saveMovieDetail(movieDetail);
		
		return movieDetail;
		
	}
	
	@GetMapping("/print_json_sample")
	public Movie JsonSample(){
		
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
		
		movie.setMovieDetail(movieDetail);
				
		
		
		Set<Tag> tagSet = new HashSet<Tag>();
		
		tag.setTagTitle("Action");
		
		tagSet.add(tag);
		
		tag.setTagTitle("Comedy");
		
		tagSet.add(tag);
		
		movie.setTags(tagSet);
		
		
		return movie;
	}
	
}
