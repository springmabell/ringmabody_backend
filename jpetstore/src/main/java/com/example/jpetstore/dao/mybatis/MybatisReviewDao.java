package com.example.jpetstore.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.jpetstore.dao.ReviewDao;
import com.example.jpetstore.dao.mybatis.mapper.ReviewMapper;
import com.example.jpetstore.domain.Review;


@Repository
public class MybatisReviewDao implements ReviewDao{

	@Autowired
	private ReviewMapper reviewMapper;


	public List<Review> getAllReviews() throws DataAccessException {
		// TODO Auto-generated method stub
		return reviewMapper.getAllReviews();
	}


	public List<Review> getReviews(String username) throws DataAccessException {
		// TODO Auto-generated method stub
		return reviewMapper.getReviewsByUsername(username);
	}

}
