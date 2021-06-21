package com.example.jpetstore.dao.mybatis;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.jpetstore.dao.ClassDao;
import com.example.jpetstore.dao.mybatis.mapper.ClassMapper;
import com.example.jpetstore.domain.Cart;
import com.example.jpetstore.domain.CartCommand;
//import com.example.jpetstore.domain.CartCommand;
import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Class;
import com.example.jpetstore.domain.Filtering;
import com.example.jpetstore.domain.PagingVO;

@Repository
public class MybatisClassDao implements ClassDao{
	
	@Autowired
	private ClassMapper classMapper;
	

	public List<Class> getAllClasses() throws DataAccessException{
		return classMapper.getAllClasses();
	}

	
	public List<Class> viewClassList(String keyword) throws DataAccessException{
		return classMapper.viewClassList(keyword);
	}

	@Override
	public List<Category> getCategoryList() throws DataAccessException {
		// TODO Auto-generated method stub
		return classMapper.getCategoryList();
	}

	@Override
	public void closeEvent(Date today) {
		// TODO Auto-generated method stub
		classMapper.closeEvent(today);
	}

	@Override
	public void writeClass(Class newClass) throws DataAccessException {
		// TODO Auto-generated method stub
		classMapper.writeClass(newClass);
	}

	@Override
	public Class findClass(int class_id) throws DataAccessException {
		// TODO Auto-generated method stub
		return classMapper.findClass(class_id);
	}

	@Override
	public void updateClass(Class oriClass) throws DataAccessException {
		// TODO Auto-generated method stub
		classMapper.updateClass(oriClass);
	}

	@Override
	public void plusHit(int class_id) throws DataAccessException {
		// TODO Auto-generated method stub
		classMapper.plusHit(class_id);
	}

	@Override
	public int countClass() throws DataAccessException {
		// TODO Auto-generated method stub
		return classMapper.countClass();
	}

	@Override
	public List<Class> filteringClass(Filtering filtering) throws DataAccessException {
		// TODO Auto-generated method stub
		return classMapper.filteringClass(filtering);
	}

	@Override
	public void deleteClass(int class_id) throws DataAccessException {
		// TODO Auto-generated method stub
		classMapper.deleteClass(class_id);
	}
	
	@Override
	public int existCart(Cart cart) throws DataAccessException {
		// TODO Auto-generated method stub
		return classMapper.existCart(cart);
	}

	@Override
	public void insertCartItem(Cart cart) throws DataAccessException {
		// TODO Auto-generated method stub
		classMapper.insertCartItem(cart);
	}

	@Override
	public List<CartCommand> findCartList(String user_id) throws DataAccessException {
		// TODO Auto-generated method stub
		return classMapper.findCartList(user_id);
	}

	@Override
	public void deleteCart(Cart cart) throws DataAccessException {
		// TODO Auto-generated method stub
		classMapper.deleteCart(cart);
	}
	

	@Override
	public void deleteFinishedClassFromCart() throws DataAccessException {
		// TODO Auto-generated method stub
		classMapper.deleteFinishedClassFromCart();
	}

}
