package com.example.jpetstore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.jpetstore.service.MainFacade;
import com.example.jpetstore.domain.Class;

//���� ������ ��Ʈ�ѷ�
@Controller
@RequestMapping("/main")
public class MainController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassController.class);

	@Autowired
	private MainFacade mainFacade;
	
	@GetMapping("/mainPage")
	public String viewMain(Model model, HttpSession session) {
		//�����ӹڼ� 3��
		List<Class> endingSoonList = mainFacade.endingSoon();
		List<Class> bestClassList = mainFacade.bestClass();
		model.addAttribute("endingSoonList", endingSoonList);
		model.addAttribute("bestClassList", bestClassList);
		
		if(session.getAttribute("userSession") != null){
			UserSession userSession1 = (UserSession)session.getAttribute("userSession");
			model.addAttribute("name", userSession1.getAccount().getUser_name());
		} else if(session.getAttribute("teacherSession") != null){
			TeacherSession teacherSession1 = (TeacherSession)session.getAttribute("teacherSession");
			model.addAttribute("name", teacherSession1.getAccount().getTeacher_name());
		}

		return "thyme/main";
	}
	
}
