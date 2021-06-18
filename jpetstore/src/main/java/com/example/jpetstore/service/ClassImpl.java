package com.example.jpetstore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpetstore.dao.ClassDao;
import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Class;
import com.example.jpetstore.domain.Filtering;
import com.example.jpetstore.domain.PagingVO;

@Service
@Transactional
public class ClassImpl implements ClassFacade{

	@Autowired
	private ClassDao classDao;
	
	public List<Class> viewClassList(PagingVO vo) {
		return classDao.viewClassList(vo);
	}

	@Override
	public List<Category> getCategoryList() {
		// TODO Auto-generated method stub
		return classDao.getCategoryList();
	}

	@Override
	public void writeClass(Class newClass) {
		// TODO Auto-generated method stub
		classDao.writeClass(newClass);
	}

	@Override
	public Class findClass(int class_id) {
		// TODO Auto-generated method stub
		return classDao.findClass(class_id);
	}

	@Override
	public void updateClass(Class oriClass) {
		// TODO Auto-generated method stub
		classDao.updateClass(oriClass);
	}

	@Override
	public void plusHit(int class_id) {
		// TODO Auto-generated method stub
		classDao.plusHit(class_id);
	}

	@Override
	public int countClass() {
		// TODO Auto-generated method stub
		return classDao.countClass();
	}

	@Override
	public List<Class> filteringClass(Filtering filtering) {
		// TODO Auto-generated method stub
		return classDao.filteringClass(filtering);
	}

	@Override
	public void deleteClass(int class_id) {
		// TODO Auto-generated method stub
		classDao.deleteClass(class_id);
	}
}
