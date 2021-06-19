package com.example.jpetstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.jpetstore.domain.TeacherAccount;
import com.example.jpetstore.service.PetStoreFacade;

@Controller
@SessionAttributes("userSession")
public class TeacherMypageController {

	private PetStoreFacade petStore;

	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

	@RequestMapping(value="/teacher/mypage.do", method=RequestMethod.GET)
	public String handleRequest(ModelMap model, @PathVariable String teacher_id) throws Exception {
		TeacherAccount teacherAccount = this.petStore.getTeacherAccount(teacher_id);
		model.put("teacherAccount", teacherAccount);
		
		return "thyme/teacher_mypage";
	}

	
}
