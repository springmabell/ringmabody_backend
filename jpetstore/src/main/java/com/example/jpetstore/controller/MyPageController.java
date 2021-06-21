package com.example.jpetstore.controller;


import java.util.List;


import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.jpetstore.domain.Order;
import com.example.jpetstore.domain.Review;
import com.example.jpetstore.domain.UserAccount;
import com.example.jpetstore.service.PetStoreFacade;

@Controller
@SessionAttributes("userSession")
@RequestMapping(value="/mypage/")
public class MyPageController {
	
	private PetStoreFacade petStore;
	
	@ModelAttribute("newReview")
	public Review newReview() {
		Review newReview = new Review();
		return newReview;
	}
	
	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}
	
	@RequestMapping(value="/info.do", method=RequestMethod.GET)
	public String handleRequest(ModelMap model, HttpSession session) throws Exception {

		UserSession userSession1 = (UserSession)session.getAttribute("userSession");
		
		System.out.println(userSession1.getAccount().getUser_id());

		model.put("user_id", userSession1.getAccount().getUser_id());
		model.put("user", userSession1.getAccount());
		model.put("name", userSession1.getAccount().getUser_name());
		
		
		return "thyme/my_info";
	}
	
	@RequestMapping(value="/review.do", method=RequestMethod.GET)
	public String ReviewRequest(ModelMap model, HttpSession session) throws Exception {
		
		UserSession userSession1 = (UserSession)session.getAttribute("userSession");
		
		
		UserAccount user = this.petStore.getUserAccount(userSession1.getAccount().getUser_id());
		List<Review> review = this.petStore.getReviews(userSession1.getAccount().getUser_id());
		int size_of_list = review.size();
		
		model.put("user", user);
		model.put("review", review);
		model.put("size", size_of_list);
		
		return "thyme/my_review";
	}
	
	@RequestMapping(value="/{review_id}/review.detail.do", method=RequestMethod.GET)
	public String ReviewDetailRequest(ModelMap model, @PathVariable int review_id) throws Exception {
		Review review = this.petStore.getReviewDetail(review_id);
		
		model.put("review", review);
		
		return "thyme/my_review_detail";
	}
	
	@RequestMapping(value="/{review_id}/review.detail.do", method=RequestMethod.POST)
	public String ReviewUpdateRequest(Review review, @PathVariable int review_id) throws Exception {
		
		petStore.updateReview(review);
		
		return "redirect:/review.do";
	}
	
	@RequestMapping(value="/purchase.do", method=RequestMethod.GET)
	public String PurchaseRequest(ModelMap model, HttpSession session) throws Exception {
		
		UserSession userSession1 = (UserSession)session.getAttribute("userSession");
		
		List<Order> orders = this.petStore.getOrdersByUsername(userSession1.getAccount().getUser_id());
		int size_of_list = orders.size();
		
		model.put("order", orders);
		model.put("size", size_of_list);
		
		return "thyme/my_purchase";
	}
}
