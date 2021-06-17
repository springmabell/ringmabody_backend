package com.example.jpetstore.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;
import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Product;
import com.example.jpetstore.service.PetStoreFacade;

/**
 * @author Juergen Hoeller
 * @since 01.12.2003
 * @modified by Changsup Park
 */
@Controller
@RequestMapping({"/teacher/join.do","/teacher/updateTeacher.do"})
public class RegisterTeacherController { 

	@Value("EditAccountForm")
	private String formViewName;
	@Value("index")
	private String successViewName;
	
	@Autowired
	private PetStoreFacade petStore;
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

//	@Autowired
//	private AccountFormValidator validator;
//	public void setValidator(AccountFormValidator validator) {
//		this.validator = validator;
//	}
		
	@ModelAttribute("teacherAccountForm")
	public TeacherAccountForm formBackingObject(HttpServletRequest request) 
			throws Exception {
		TeacherSession teacherSession = 
			(TeacherSession) WebUtils.getSessionAttribute(request, "teacherSession");
//		if (userSession != null) {	// edit an existing accoun
//			System.out.println("pass 1");
//			
//			System.out.println(userSession.getAccount().getUser_id());
//			return new UserAccountForm(
//				petStore.getUserAccount(userSession.getAccount().getUser_id()));
//		}
//		else {	// create a new account
			return new TeacherAccountForm();
//		}
	}


	
	@RequestMapping(method = RequestMethod.GET)
	public String showForm() {
		return formViewName;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(
			HttpServletRequest request, HttpSession session,
			@ModelAttribute("teacherAccountForm") TeacherAccountForm teacherAccountForm,
			BindingResult result) throws Exception {

//		if (request.getParameter("account.listOption") == null) {
//			userAccountForm.getAccount().setListOption(false);
//		}
//		if (request.getParameter("account.bannerOption") == null) {
//			userAccountForm.getAccount().setBannerOption(false);
//		}

//		validator.validate(userAccountForm, result);
		
		if (result.hasErrors()) return formViewName;
		try {
			if (teacherAccountForm.isNewAccount()) {
				petStore.insertTeacherAccount(teacherAccountForm.getAccount());
			}
			else {
				petStore.updateTeacherAccount(teacherAccountForm.getAccount());
			}
		}
		catch (DataIntegrityViolationException ex) {
			result.rejectValue("account.teacher_id", "TEACHER_ID_ALREADY_EXISTS",
					"Teacher ID already exists: choose a different ID.");
			return formViewName; 
		}
		
		TeacherSession teacherSession = new TeacherSession(
			petStore.getTeacherAccount(teacherAccountForm.getAccount().getTeacher_id()));
		
//		PagedListHolder<Product> myList = new PagedListHolder<Product>(
//			petStore.getProductListByCategory(accountForm.getAccount().getFavouriteCategoryId()));
//		myList.setPageSize(4);
//		userSession.setMyList(myList);
		
		session.setAttribute("teacherSession", teacherSession);
		return successViewName;  
	}
}
