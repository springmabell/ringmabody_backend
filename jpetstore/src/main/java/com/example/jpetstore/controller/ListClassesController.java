package com.example.jpetstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Class;
import com.example.jpetstore.domain.Product;
import com.example.jpetstore.domain.TeacherAccount;
import com.example.jpetstore.domain.UserAccount;
import com.example.jpetstore.service.ClassFacade;
import com.example.jpetstore.service.PetStoreFacade;

/**
 * @author Juergen Hoeller
 * @since 01.12.2003
 * @modified by Changsup Park
 */
@Controller
@SessionAttributes({"userSession","teacherSession"})
public class ListClassesController {

	private PetStoreFacade petStore;
	
	@Autowired
	private ClassFacade classes;

	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

	@RequestMapping("/admin/listClasses.do")
	public String handleRequest(ModelMap model) throws Exception {
		
		List<Class> classList = this.classes.getAllClasses();
		int size_of_list = classList.size();
		

		System.out.println("hi");
		
		System.out.println(size_of_list);
		
		model.put("classList", classList);
		model.put("size", size_of_list);
		
		
		return "thyme/admin_bulletin";
	}


}
