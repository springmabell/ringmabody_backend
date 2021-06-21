package com.example.jpetstore.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;
import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Class;
import com.example.jpetstore.domain.Product;
import com.example.jpetstore.service.MainFacade;
import com.example.jpetstore.service.PetStoreFacade;

/**
 * @author Juergen Hoeller
 * @since 01.12.2003
 * @modified by Changsup Park
 */
@Controller
@RequestMapping({"/user/join.do","/user/updateUser.do"})
@SessionAttributes("userSession")
public class RegisterUserController { 

	@Value("thyme/join_user_form")
	private String formViewName;
	

	@Autowired
	private MainFacade mainFacade;
	
	@Autowired
	private PetStoreFacade petStore;
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

	@ModelAttribute("userAccountForm")
	public UserAccountForm formBackingObject(HttpServletRequest request) 
			throws Exception {
		UserSession userSession = 
			(UserSession) WebUtils.getSessionAttribute(request, "userSession");
			return new UserAccountForm();
	}


	
	@RequestMapping(method = RequestMethod.GET)
	public String showForm() {
		return formViewName;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(
			HttpServletRequest request, HttpSession session,
			@ModelAttribute("userAccountForm") UserAccountForm userAccountForm,
			Model model,
			BindingResult result) throws Exception {

		
		if (result.hasErrors()) return formViewName;
		try {
			if (userAccountForm.isNewAccount()) {
				petStore.insertUserAccount(userAccountForm.getAccount());
			}
			else {
				petStore.updateUserAccount(userAccountForm.getAccount());
			}
		}
		catch (DataIntegrityViolationException ex) {
			result.rejectValue("account.username", "USER_ID_ALREADY_EXISTS",
					"User ID already exists: choose a different ID.");
			return formViewName; 
		}
		
		UserSession userSession = new UserSession(
			petStore.getUserAccount(userAccountForm.getAccount().getUser_id()));
		
		session.setAttribute("userSession", userSession);
		

		List<Class> endingSoonList = mainFacade.endingSoon();
		List<Class> bestClassList = mainFacade.bestClass();
		model.addAttribute("endingSoonList", endingSoonList);
		model.addAttribute("bestClassList", bestClassList);
		model.addAttribute("name", userSession.getAccount().getUser_name());

		
		return "thyme/main";  
	}
}









