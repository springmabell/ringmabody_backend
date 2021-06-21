package com.example.jpetstore.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.example.jpetstore.domain.Class;
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
	
	/*// 클래스 폼 작성 get
	@RequestMapping(value="/{order_id}/review.write.do", method=RequestMethod.GET)
	public String writeClass() {
		return "thyme/my_review_write";
	}*/

/*	// 클래스 폼 작성 post
	@RequestMapping(value="/{order_id}/review.write.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String insertClass(@Valid Review newReview, @PathVariable int order_id, BindingResult result, SessionStatus status,
			HttpServletRequest request, HttpSession session) {
		if (result.hasErrors()) {
			return "thyme/my_review_write";
		}

		MultipartFile mf = newReview.getReport();
		String savedName = uploadFile(mf, request);

		newReview.setReview_img(savedName);
		
		UserSession userSession1 = (UserSession)session.getAttribute("userSession");
		String userId = userSession1.getAccount().getUser_id();
		System.out.println(userId);
		newReview.setUser_id(userId);
		newReview.setOrder_id(order_id);

		petStore.writeReview(newReview);

		return "thyme/finish";
	}

		private String uploadFile(MultipartFile report, HttpServletRequest request) {
			UUID uuid = UUID.randomUUID();

			String savedName = uuid.toString() + "_" + report.getOriginalFilename();

			String storagePath = request.getServletContext().getRealPath("/images/" + savedName);
			File file = new File(storagePath);
			try {
				report.transferTo(file);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			return savedName;
		}*/
	
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
