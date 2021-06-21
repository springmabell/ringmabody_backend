package com.example.jpetstore.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.jpetstore.domain.Review;

@Mapper
public interface ReviewMapper {
	
	List<Review> getAllReviews();
	
	List<Review> getReviewsByUsername(String username);

	Review getReviewDetail(int review_id);

	void writeReview(Review newReview);

	void updateReview(Review review);
}
