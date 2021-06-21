package com.example.jpetstore.service;

import java.util.List;

import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Class;
import com.example.jpetstore.domain.Item;
import com.example.jpetstore.domain.Order;
import com.example.jpetstore.domain.Product;
import com.example.jpetstore.domain.Review;
import com.example.jpetstore.domain.TeacherAccount;
import com.example.jpetstore.domain.UserAccount;

/**
 * JPetStore's central business interface.
 *
 * @author Juergen Hoeller
 * @since 30.11.2003
 */
public interface PetStoreFacade {

	List<Category> getCategoryList();

	Category getCategory(String categoryId);
	

	List<Product> getProductListByCategory(String categoryId);

	List<Product> searchProductList(String keywords);

	Product getProduct(String productId);


	List<Item> getItemListByProduct(String productId);

	Item getItem(String itemId);

	boolean isItemInStock(String itemId);


	void insertOrder(Order order);

	Order getOrder(int orderId);

	List<Order> getOrdersByUsername(String username);
	
	
	// 여기서부터 저희가 만든 것 
	
	List<TeacherAccount> getAllTeacherAccount();
	
	List<UserAccount> getAllUserAccount();
	
	List<TeacherAccount> getAllClass();
	
	UserAccount getUserAccount(String username);
	
	UserAccount getUserAccount(String username, String password);
	

	void insertUserAccount(UserAccount account);

	void updateUserAccount(UserAccount account);

	TeacherAccount getTeacherAccount(String teacher_id);

	TeacherAccount getTeacherAccount(String teacher_id, String teacher_pwd);

	void insertTeacherAccount(TeacherAccount account);

	void updateTeacherAccount(TeacherAccount account);

	List<Review> getAllReviews();
	
	List<Review> getReviews(String username);

	Review getReviewDetail(int review_id);
	
	public void writeReview(Review newReview);

}
