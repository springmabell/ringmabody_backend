package com.example.jpetstore.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.jpetstore.domain.Class;

public interface MainDao {
	public List<Class> endingSoon() throws DataAccessException;
	public List<Class> bestClass() throws DataAccessException;
}
