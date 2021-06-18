package com.example.jpetstore.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.jpetstore.dao.MainDao;
import com.example.jpetstore.dao.mybatis.mapper.MainMapper;
import com.example.jpetstore.domain.Class;

@Repository
public class MybatisMainDao implements MainDao{
	
	@Autowired
	private MainMapper mainMapper;

	@Override
	public List<Class> endingSoon() throws DataAccessException {
		// TODO Auto-generated method stub
		return mainMapper.endingSoon();
	}

	@Override
	public List<Class> bestClass() throws DataAccessException {
		// TODO Auto-generated method stub
		return mainMapper.bestClass();
	}
	
}
