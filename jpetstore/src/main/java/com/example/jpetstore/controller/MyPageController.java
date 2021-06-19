package com.example.jpetstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.jpetstore.domain.Review;
import com.example.jpetstore.domain.UserAccount;
import com.example.jpetstore.service.PetStoreFacade;


@Controller
@SessionAttributes("userSession")
@RequestMapping(value="/{user_id}/mypage/")
public class MyPageController {
	
	private PetStoreFacade petStore;
	
	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}
	
	@RequestMapping(value="/info.do", method=RequestMethod.GET)
	public String handleRequest(ModelMap model, @PathVariable String user_id) throws Exception {
		UserAccount user = this.petStore.getUserAccount(user_id);
		model.put("user", user);
		
		return "thyme/my_info";
	}
	
	@RequestMapping(value="/review.do", method=RequestMethod.GET)
	public String Request(ModelMap model, @PathVariable String user_id) throws Exception {
		UserAccount user = this.petStore.getUserAccount(user_id);
		List<Review> review = this.petStore.getReviews(user_id);
		int size_of_list = review.size();
		
		model.put("user", user);
		model.put("review", review);
		model.put("size", size_of_list);
		
		return "thyme/my_review";
	}
	
}
