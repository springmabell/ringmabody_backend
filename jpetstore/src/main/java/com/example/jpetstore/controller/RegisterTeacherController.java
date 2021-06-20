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

	@Value("thyme/join_teacher_form")
	private String formViewName;
	
	@Autowired
	private PetStoreFacade petStore;
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

		
	@ModelAttribute("teacherAccountForm")
	public TeacherAccountForm formBackingObject(HttpServletRequest request) 
			throws Exception {
		TeacherSession teacherSession = 
			(TeacherSession) WebUtils.getSessionAttribute(request, "teacherSession");
			return new TeacherAccountForm();
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
		
		session.setAttribute("teacherSession", teacherSession);
		
		return "thyme/main";  
	}
}
