package com.example.jpetstore.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.jpetstore.domain.Review;

public interface ReviewDao {

	List<Review> getAllReviews() throws DataAccessException;
	
	List<Review> getReviews(String username) throws DataAccessException;

	Review getReviewDetail(int review_id);

	void writeReview(Review newReview);

	void updateReview(Review review);
	
	void deleteReview(int review_id);
}
