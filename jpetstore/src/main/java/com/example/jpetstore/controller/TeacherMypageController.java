package com.example.jpetstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.jpetstore.domain.Order;
import com.example.jpetstore.domain.TeacherAccount;
import com.example.jpetstore.service.PetStoreFacade;

@Controller
@SessionAttributes("teacherSession")
@RequestMapping(value="/{teacher_id}/mypage/")
public class TeacherMypageController {

	private PetStoreFacade petStore;

	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

	@RequestMapping(value="/viewTeacher.do", method=RequestMethod.GET)
	public String handleRequest(ModelMap model, @PathVariable String teacher_id) throws Exception {
		TeacherAccount teacherAccount = this.petStore.getTeacherAccount(teacher_id);
		model.put("teacherAccount", teacherAccount);
		
		return "thyme/teacher_info";
	}
	
	@RequestMapping(value="/mypage/classList.do", method=RequestMethod.GET)
	public String ReviewDetailRequest(ModelMap model, @PathVariable String teacher_id) throws Exception {
		TeacherAccount teacherAccount = this.petStore.getTeacherAccount(teacher_id);
		
		model.put("teacherAccount", teacherAccount);
		
		return "thyme/teacher_classDetail";
	}
	
	@RequestMapping(value="/createClass.do", method=RequestMethod.GET)
	public String CreateClassRequest(ModelMap model, @PathVariable String teacher_id) throws Exception {
		TeacherAccount teacherAccount = this.petStore.getTeacherAccount(teacher_id);
		
		model.put("teacherAccount", teacherAccount);
		
		return "thyme/teacher_register";
	}
	
	
	@RequestMapping(value="/listTeacherPayments.do", method=RequestMethod.GET)
	public String PurchaseRequest(ModelMap model, @PathVariable String teacher_id) throws Exception {
		TeacherAccount teacherAccount = this.petStore.getTeacherAccount(teacher_id);
		List<Order> orders = this.petStore.getOrdersByUsername(teacher_id);
		int size_of_list = orders.size();
		
		model.put("teacherAccount", teacherAccount);
		model.put("order", orders);
		model.put("size", size_of_list);
		
		
		return "thyme/teacher_payment";
	}
}

	

