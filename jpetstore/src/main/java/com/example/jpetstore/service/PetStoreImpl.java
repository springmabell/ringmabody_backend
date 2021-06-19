package com.example.jpetstore.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.jpetstore.dao.AccountDao;
import com.example.jpetstore.dao.CategoryDao;
import com.example.jpetstore.dao.ItemDao;
import com.example.jpetstore.dao.OrderDao;
import com.example.jpetstore.dao.ProductDao;
import com.example.jpetstore.dao.ReviewDao;
import com.example.jpetstore.dao.TeacherAccountDao;
import com.example.jpetstore.dao.UserAccountDao;
import com.example.jpetstore.domain.Account;
import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Item;
import com.example.jpetstore.domain.Order;
import com.example.jpetstore.domain.Product;
import com.example.jpetstore.domain.Review;
import com.example.jpetstore.domain.TeacherAccount;
import com.example.jpetstore.domain.UserAccount;

@Service
@Transactional
public class PetStoreImpl implements PetStoreFacade { 
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private TeacherAccountDao teacherAccountDao;
	@Autowired
	private UserAccountDao userAccountDao;
	@Autowired
	private ReviewDao reviewDao;
	

	//-------------------------------------------------------------------------
	// Operation methods, implementing the PetStoreFacade interface
	//-------------------------------------------------------------------------

	public Account getAccount(String username) {
		return accountDao.getAccount(username);
	}

	public Account getAccount(String username, String password) {
		return accountDao.getAccount(username, password);
	}

	public void insertAccount(Account account) {
		accountDao.insertAccount(account);
	}

	public void updateAccount(Account account) {
		accountDao.updateAccount(account);
	}

	public List<String> getUsernameList() {
		return accountDao.getUsernameList();
	}

	public List<Category> getCategoryList() {
		return categoryDao.getCategoryList();
	}

	public Category getCategory(String categoryId) {
		return categoryDao.getCategory(categoryId);
	}

	public List<Product> getProductListByCategory(String categoryId) {
		return productDao.getProductListByCategory(categoryId);
	}

	public List<Product> searchProductList(String keywords) {
		return productDao.searchProductList(keywords);
	}

	public Product getProduct(String productId) {
		return productDao.getProduct(productId);
	}

	public List<Item> getItemListByProduct(String productId) {
		return itemDao.getItemListByProduct(productId);
	}

	public Item getItem(String itemId) {
		return itemDao.getItem(itemId);
	}

	public boolean isItemInStock(String itemId) {
		return itemDao.isItemInStock(itemId);
	}

	public void insertOrder(Order order) {
		itemDao.updateQuantity(order);	    
		orderDao.insertOrder(order);
	}
	
	public Order getOrder(int orderId) {
		return orderDao.getOrder(orderId);
	}

	public List<Order> getOrdersByUsername(String username) {
		return orderDao.getOrdersByUsername(username);
	}
	
	// 여기서부터 저희가 쓴 코드 

	public List<TeacherAccount> getAllTeacherAccount() {
		return teacherAccountDao.getAllTeacherAccount();
	};
	
	public List<TeacherAccount> getAllClass() {
		// TODO Auto-generated method stub
		return teacherAccountDao.getAllClass();
	}
	
	public List<UserAccount> getAllUserAccount() {
		return userAccountDao.getAllUserAccount();
	};
	
	public UserAccount getUserAccount(String username) {
		return userAccountDao.getUserAccount(username);
	}

	public UserAccount getUserAccount(String username, String password) {
		return userAccountDao.getUserAccount(username, password);
	}
	
	public void insertUserAccount(UserAccount account) {
		userAccountDao.insertUserAccount(account);
	}

	public void updateUserAccount(UserAccount account) {
		userAccountDao.updateUserAccount(account);
	}

	@Override
	public TeacherAccount getTeacherAccount(String teacher_id) {
		// TODO Auto-generated method stub
		return teacherAccountDao.getTeacherAccount(teacher_id);
	}

	@Override
	public TeacherAccount getTeacherAccount(String teacher_id, String teacher_pwd) {
		// TODO Auto-generated method stub
		return teacherAccountDao.getTeacherAccount(teacher_id, teacher_pwd);
	}

	@Override
	public void insertTeacherAccount(TeacherAccount account) {
		// TODO Auto-generated method stub
		teacherAccountDao.insertTeacherAccount(account);
	}

	@Override
	public void updateTeacherAccount(TeacherAccount account) {
		// TODO Auto-generated method stub
		teacherAccountDao.updateTeacherAccount(account);
	}
	
	@Override
	public List<Review> getReviews(String user_id) {
		// TODO Auto-generated method stub
		return reviewDao.getReviews(user_id);
	}

	@Override
	public List<Review> getAllReviews() {
		// TODO Auto-generated method stub
		return reviewDao.getAllReviews();
	}

}
