package com.example.jpetstore.dao.mybatis.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.jpetstore.domain.Cart;
import com.example.jpetstore.domain.CartCommand;
//import com.example.jpetstore.domain.CartCommand;
import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Class;
import com.example.jpetstore.domain.Filtering;
import com.example.jpetstore.domain.PagingVO;

@Mapper
public interface ClassMapper {
	public List<Class> viewClassList(PagingVO vo);
	public List<Category> getCategoryList();
	public void closeEvent(Date today);
	public void writeClass(Class newClass);
	public Class findClass(int class_id);
	public void updateClass(Class oriClass);
	public void plusHit(int class_id);
	public int countClass();
	public List<Class> filteringClass(Filtering filtering);
	public void deleteClass(int class_id);
	public int existCart(Cart cart);
	public void insertCartItem(Cart cart);
	public List<CartCommand> findCartList(String user_id);
	public void deleteCart(Cart cart);
	public void deleteFinishedClassFromCart();
}
