package com.example.demo.resources;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Rating;
import com.example.demo.models.UserRating;

import brave.Span;
import brave.Tracer;
import brave.Tracing;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {
	
	@Autowired
	Tracer tracer;
	@Autowired
	Tracing tracing;
	
	
	Logger LOGGER = LoggerFactory.getLogger(RatingResource.class);

	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId,4);
	}
	
	@RequestMapping("users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		
		LOGGER.info("...... Inside Rating Service");
		Span span =  tracer.currentSpan();
		
		System.out.println("tracer.currentSpan().context() = "+tracer.currentSpan().context());
		System.out.println("Span in User Rating = "+ span);
		System.out.println("span.isNoop() = "+span.isNoop());
		System.out.println("tracer.currentSpan().context().parentIdString() = "+tracer.currentSpan().context().parentIdString());
		
		
		
		
		
		
		
//		 Logic
		List<Rating> ratings = Arrays.asList(
				new Rating("1234",8),
				new Rating("5678",6)
				);
		
		UserRating userRating  = new UserRating();
		userRating.setUserRating(ratings);
		return userRating;
	}
	
}
