package com.example.jpetstore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.jpetstore.domain.Class;
import com.example.jpetstore.service.MainFacade;

/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 * @modified-by Changsup Park
 */
@Controller
public class SignoffController { 

	private static final Logger LOGGER = LoggerFactory.getLogger(ClassController.class);

	@Autowired
	private MainFacade mainFacade;
	
	@RequestMapping("/user/signoff.do")
	public String handleRequest(HttpSession session, Model model) throws Exception {

		List<Class> endingSoonList = mainFacade.endingSoon();
		List<Class> bestClassList = mainFacade.bestClass();
		model.addAttribute("endingSoonList", endingSoonList);
		model.addAttribute("bestClassList", bestClassList);
		

		session.removeAttribute("teacherSession");
		session.removeAttribute("userSession");
		session.invalidate();
		return "thyme/main";
	}
}
