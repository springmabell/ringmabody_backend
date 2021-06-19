package com.example.jpetstore.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.jpetstore.domain.Cart;
import com.example.jpetstore.domain.CartCommand;
//import com.example.jpetstore.domain.CartCommand;
import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Class;
import com.example.jpetstore.domain.Filtering;
import com.example.jpetstore.domain.PagingVO;

public interface ClassDao {
	
	public List<Class> viewClassList(PagingVO vo) throws DataAccessException;
	public List<Category> getCategoryList() throws DataAccessException;
	public void closeEvent(Date today);
	public void writeClass(Class newClass) throws DataAccessException;
	public Class findClass(int class_id) throws DataAccessException;
	public void updateClass(Class oriClass) throws DataAccessException;
	public void plusHit(int class_id) throws DataAccessException;
	public int countClass() throws DataAccessException;
	public List<Class> filteringClass(Filtering filtering)throws DataAccessException;
	public void deleteClass(int class_id) throws DataAccessException;
	public int existCart(Cart cart) throws DataAccessException;
	public void insertCartItem(Cart cart) throws DataAccessException;
	public List<CartCommand> findCartList(String user_id) throws DataAccessException;
	public void deleteCart(Cart cart) throws DataAccessException;
}
