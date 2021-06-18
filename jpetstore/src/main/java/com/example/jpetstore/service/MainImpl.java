package com.example.jpetstore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpetstore.dao.MainDao;
import com.example.jpetstore.domain.Class;

@Service
@Transactional
public class MainImpl implements MainFacade{
	@Autowired
	private MainDao mainDao;

	@Override
	public List<Class> endingSoon() {
		// TODO Auto-generated method stub
		return mainDao.endingSoon();
	}

	@Override
	public List<Class> bestClass() {
		// TODO Auto-generated method stub
		return mainDao.bestClass();
	}
	
}
