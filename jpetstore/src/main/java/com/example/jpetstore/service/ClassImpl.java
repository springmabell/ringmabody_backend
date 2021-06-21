package com.example.jpetstore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpetstore.dao.ClassDao;
import com.example.jpetstore.domain.Cart;
import com.example.jpetstore.domain.CartCommand;
//import com.example.jpetstore.domain.CartCommand;
import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Class;
import com.example.jpetstore.domain.Filtering;
import com.example.jpetstore.domain.PagingVO;

@Service
@Transactional
public class ClassImpl implements ClassFacade{

	@Autowired
	private ClassDao classDao;
	
	public List<Class> getAllClasses(){
		return classDao.getAllClasses();
	}
	
	public List<Class> viewClassList(String keyword) {
		return classDao.viewClassList(keyword);
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
	@Override
	public int existCart(Cart cart) {
		// TODO Auto-generated method stub
		return classDao.existCart(cart);
	}

	@Override
	public void insertCartItem(Cart cart) {
		// TODO Auto-generated method stub
		classDao.insertCartItem(cart);
	}

	@Override
	public List<CartCommand> findCartList(String user_id) {
		// TODO Auto-generated method stub
		return classDao.findCartList(user_id);
	}

	@Override
	public void deleteCart(Cart cart) {
		// TODO Auto-generated method stub
		classDao.deleteCart(cart);
	}
}
