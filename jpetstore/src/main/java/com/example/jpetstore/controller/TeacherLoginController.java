package com.example.jpetstore.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.jpetstore.domain.Account;
import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Class;
import com.example.jpetstore.domain.Filtering;
import com.example.jpetstore.domain.Product;
import com.example.jpetstore.domain.TeacherAccount;
import com.example.jpetstore.domain.UserAccount;
import com.example.jpetstore.service.ClassFacade;
import com.example.jpetstore.service.MainFacade;
import com.example.jpetstore.service.PetStoreFacade;
import com.example.jpetstore.service.SchedulerFacade;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;

/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 * @modified-by Changsup Park
 */
@Controller
@SessionAttributes({"userSession","teacherSession"})
public class TeacherLoginController { 
	

	private static final Logger LOGGER = LoggerFactory.getLogger(ClassController.class);

	private PetStoreFacade petStore;

	@Autowired
	private MainFacade mainFacade;
	@Autowired
	private ClassFacade classFacade;

	@Autowired
	private SchedulerFacade schedulerFacade; // 스케줄러 서비스 인터페이스

	@ModelAttribute("newClass")
	public Class newClass() {
		Class newClass = new Class();
		return newClass;
	}

	@ModelAttribute("categoryList")
	public List<Category> getCategoryList() {
		List<Category> categoryList = classFacade.getCategoryList();
		return categoryList;
	}

	@ModelAttribute("localList")
	public String[] getLocalList() {
		return new String[] { "서울", "경기", "강원", "충남", "충북", "전남", "전북", "경남", "경북", "부산", "대구", "인천", "광주", "세종", "대전",
				"울산", "제주" };
	}

	@ModelAttribute("filtering")
	public Filtering formBacking(HttpServletRequest request) throws Exception {
		return new Filtering();
	}
	
	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}
	
	@GetMapping("/teacher/loginForm.do")
	public String loginForm() {
		return "thyme/login_teacher";
	}

	@GetMapping("/teacher/joinAgreeForm.do")
	public String joinAgreeForm() {
		return "thyme/join_teacher_agree";
	}
	

	@GetMapping("/teacher/joinForm.do")
	public String joingForm() {
		return "thyme/join_teacher_form";
	}
	

	@RequestMapping("/teacher/login.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			@RequestParam("teacher_id") String teacher_id,
			@RequestParam("teacher_pwd") String teacher_pwd,
			@RequestParam(value="forwardAction", required=false) String forwardAction,
			HttpSession session,
			@RequestParam(required = false) String keyword,
			Model model) throws Exception {
		
		
		TeacherAccount account = petStore.getTeacherAccount(teacher_id, teacher_pwd);
		if (account == null) {
			return new ModelAndView("Error", "message", 
					"Invalid id or password.  Signon failed.");
		}
		else {
			TeacherSession teacherSession = new TeacherSession(account);
			
			List<UserAccount> userList = this.petStore.getAllUserAccount();
			int size_of_list = userList.size();
			
			model.addAttribute("userList", userList);
			model.addAttribute("size", size_of_list);
			
			model.addAttribute("teacherSession", teacherSession);
			model.addAttribute("name", teacherSession.getAccount().getTeacher_name());
			
			session.setAttribute("teacherSession", teacherSession);
			

			List<Class> endingSoonList = mainFacade.endingSoon();
			List<Class> bestClassList = mainFacade.bestClass();
			model.addAttribute("endingSoonList", endingSoonList);
			model.addAttribute("bestClassList", bestClassList);
			model.addAttribute("usertype", "teacher");
			

			/* ViewClassList */
			Filtering filtering = new Filtering();
			List<String> checkedCategory = new ArrayList<String>();
			String checkedLocal = "dd";
			// value1 will be checked by default.
			checkedCategory.add("value1");
			filtering.setCheckedLocal(checkedLocal);
			filtering.setCheckedCategory(checkedCategory);
			model.addAttribute("filtering", filtering);
			
			List<Class> classList = classFacade.viewClassList(keyword);
			Date today = new Date();
			long t = today.getTime();
			for (Class c : classList) {
				long e = c.getEdate().getTime();
				long cal = e - t;
				long calcDate = cal / (24 * 60 * 60 * 1000);
				if (calcDate > 0) {
					c.setDate(calcDate + 1);
				} else if (calcDate == 0) {
					c.setDate(0);
				} else {
					c.setDate(-1);
				}
			}
			/* */
			
			if (forwardAction != null) 
				return new ModelAndView("redirect:" + forwardAction);
			else 
				return new ModelAndView("thyme/ViewClassList");
		}
	}
	
	
}
