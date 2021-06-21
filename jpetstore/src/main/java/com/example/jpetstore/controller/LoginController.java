package com.example.jpetstore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.jpetstore.domain.Account;
import com.example.jpetstore.domain.Class;
import com.example.jpetstore.domain.Product;
import com.example.jpetstore.domain.UserAccount;
import com.example.jpetstore.service.MainFacade;
import com.example.jpetstore.service.PetStoreFacade;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;

/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 * @modified-by Changsup Park
 */
@Controller
@SessionAttributes({"userSession","teacherSession"})
public class LoginController { 

	private PetStoreFacade petStore;
	
	@Autowired
	private MainFacade mainFacade;
	
	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}
	
	@GetMapping("/user/loginForm.do")
	public String loginForm(){
		return "thyme/login";
	}
	
	@GetMapping("/user/joinAgreeForm.do")
	public String joinAgreeForm() {
		return "thyme/join_user_agree";
	}
	
	@GetMapping("/user/joinForm.do")
	public String joingForm() {
		return "thyme/join_user_form";
	}

	@RequestMapping("/user/login.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam(value="forwardAction", required=false) String forwardAction,
			HttpSession session,
			Model model) throws Exception {
		
		
		UserAccount account = petStore.getUserAccount(username, password);
		
		if (account == null) {
			System.out.println("account null하대요 ");
			return new ModelAndView("Error", "message", 
					"Invalid username or password.  Signon failed.");
		}
		
		else {
			UserSession userSession = new UserSession(account);
			
			model.addAttribute("userSession", userSession);
			model.addAttribute("name", userSession.getAccount().getUser_name());
			model.addAttribute("user_id", userSession.getAccount().getUser_id());
			
			session.setAttribute("userSession", userSession);
			System.out.println(userSession.getAccount().getUser_name());
			

			List<Class> endingSoonList = mainFacade.endingSoon();
			List<Class> bestClassList = mainFacade.bestClass();
			model.addAttribute("endingSoonList", endingSoonList);
			model.addAttribute("bestClassList", bestClassList);
			
			if (forwardAction != null) 
				return new ModelAndView("redirect:" + forwardAction);
			else 
				return new ModelAndView("thyme/main");
		}
	}
}
