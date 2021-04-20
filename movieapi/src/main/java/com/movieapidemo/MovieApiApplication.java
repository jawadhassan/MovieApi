package com.movieapidemo;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.movieapidemo.data.MovieRepository;
import com.movieapidemo.entity.KeyWord;
import com.movieapidemo.entity.Movie;
import com.movieapidemo.entity.MovieDetail;
import com.movieapidemo.entity.MovieReview;

@SpringBootApplication
public class MovieApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieApiApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner dataLoader(MovieRepository movieRepo) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				
				MovieDetail movieDetail = new MovieDetail();
				
				movieDetail.setBudget(1000);
				movieDetail.setRevenue(1000);
				movieDetail.setVoteCount(1);
				
				
				LocalTime end = LocalTime.parse("03:00:00");

				LocalTime start = LocalTime.parse("00:00:00");

				Duration duration = Duration.between(start, end);
				
				movieDetail.setRuntime(duration);
				
				KeyWord keyWord = new KeyWord();
				
				List<KeyWord> tagSet = new ArrayList<KeyWord>();

				keyWord.setKeywordTitle("Action");

				tagSet.add(keyWord);

				keyWord.setKeywordTitle("Comedy");

				tagSet.add(keyWord);
				
				MovieReview movieReview = new MovieReview();

				movieReview.setAuthor("James");

				movieReview.setContent("Good movie");

				movieReview.setEmail("sdfghjkl");


			//List<MovieReview> reviewCollection = new Arraylist<MovieReview>();

				List<MovieReview> reviewCollection = new ArrayList<MovieReview>();
				
				reviewCollection.add(movieReview);

				
				Movie movie = new Movie();
				movie.setStatus("Released");
				movie.setTagline("Check it out");
				movie.setTitle("Check Mate");
				movie.setReleaseDate(new Date());
				movie.setMovieReviews(reviewCollection);
				movie.setKeyWords(tagSet);
				movie.setMovieDetail(movieDetail);
				movieRepo.save(movie);
				
			}
		};
	}
}
