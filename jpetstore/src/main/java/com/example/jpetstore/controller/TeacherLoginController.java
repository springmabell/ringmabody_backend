package com.example.jpetstore.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.jpetstore.domain.Account;
import com.example.jpetstore.domain.Product;
import com.example.jpetstore.domain.TeacherAccount;
import com.example.jpetstore.domain.UserAccount;
import com.example.jpetstore.service.PetStoreFacade;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;

/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 * @modified-by Changsup Park
 */
@Controller
@SessionAttributes("teacherSession")
public class TeacherLoginController { 

	private PetStoreFacade petStore;
	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

	@RequestMapping("/teacher/login.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			@RequestParam("teacher_id") String teacher_id,
			@RequestParam("teacher_pwd") String teacher_pwd,
			@RequestParam(value="forwardAction", required=false) String forwardAction,
			Model model) throws Exception {
		
		
		TeacherAccount account = petStore.getTeacherAccount(teacher_id, teacher_pwd);
		if (account == null) {
			return new ModelAndView("Error", "message", 
					"Invalid id or password.  Signon failed.");
		}
		else {
			TeacherSession teacherSession = new TeacherSession(account);
			
//			PagedListHolder<Product> myList = new PagedListHolder<Product>(this.petStore.getProductListByCategory(account.getFavouriteCategoryId()));
//			myList.setPageSize(4);
//			userSession.setMyList(myList);
			
			model.addAttribute("teacherSession", teacherSession);
			if (forwardAction != null) 
				return new ModelAndView("redirect:" + forwardAction);
			else 
				return new ModelAndView("thyme/teacher_mypage");
		}
	}
	
	
}
