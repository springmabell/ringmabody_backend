package com.example.jpetstore.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.jpetstore.domain.Review;

public interface ReviewDao {

	List<Review> getAllReviews() throws DataAccessException;
	
	List<Review> getReviews(String username) throws DataAccessException;
}
