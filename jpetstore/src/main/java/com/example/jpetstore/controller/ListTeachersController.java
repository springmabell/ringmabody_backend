package com.example.jpetstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.jpetstore.domain.TeacherAccount;
import com.example.jpetstore.service.PetStoreFacade;

/**
 * @author Juergen Hoeller
 * @since 01.12.2003
 * @modified by Changsup Park
 */
@Controller
@SessionAttributes("userSession")
public class ListTeachersController {

	private PetStoreFacade petStore;

	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

	@RequestMapping("/admin/listTeachers.do")
	public String handleRequest(ModelMap model) throws Exception {
		List<TeacherAccount> teacherList = this.petStore.getAllTeacherAccount();
		int size_of_list = teacherList.size();
		
		model.put("teacherList", teacherList);
		model.put("size", size_of_list);
		
		
		return "thyme/admin_teacher_list";
	}


}
