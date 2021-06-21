package com.example.jpetstore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.jpetstore.service.ClassFacade;
import com.example.jpetstore.service.OrderFacade;
import com.example.jpetstore.domain.Cart;
import com.example.jpetstore.domain.Class;
import com.example.jpetstore.domain.Order;

@Controller
@RequestMapping
@SessionAttributes({"orderList", "amount"})
public class OrderController {

	@Autowired
	private ClassFacade classFacade;

	@Autowired
	private OrderFacade orderFacade;
	
	@ModelAttribute("newOrder")
	public Order newOrder() {
			return new Order();
	}
	

	@ModelAttribute("cardBankList")
	public List<String> getCardBankList() {
		List<String> cardBankList = new ArrayList<String>();
		cardBankList.add("SHINHAN");
		cardBankList.add("KOOKMIN");
		cardBankList.add("NONGHYUP");
		cardBankList.add("WOORI");
		cardBankList.add("IBK");
		cardBankList.add("CITI");
		return cardBankList;
	}
	

	@ModelAttribute("name")
	public String returnName(HttpSession session, Model model) {
		String name="";
		if(session.getAttribute("userSession") != null){
			UserSession userSession1 = (UserSession)session.getAttribute("userSession");
			name = userSession1.getAccount().getUser_name();
		} else if(session.getAttribute("teacherSession") != null){
			TeacherSession teacherSession1 = (TeacherSession)session.getAttribute("teacherSession");
			name = teacherSession1.getAccount().getTeacher_name();
		}
		return name;
	}
	
	//장바구니에서 선택한 클래스들의 아이디들의 배열을 view에서 가져와서 class 배열에 클래스 아이디 이용해 찾은 클래스들을 넣는다. 결제 페이지로 가게 설정
	@PostMapping("/order/cart")
	public String orderCart(@RequestParam(value = "array") List<String> chArr, @RequestParam("amount") String amount, Model model) {
		int i;
		Class findClass;
		List<Class> orderList = new ArrayList<Class>();
		int size = chArr.size();
		for(i = 0; i < size; i++) {
			findClass = classFacade.findClass(Integer.parseInt(chArr.get(i)));
			orderList.add(findClass);
		}
		model.addAttribute("orderList", orderList);
		model.addAttribute("amount", amount);
	
		return "thyme/Order";
	}
	
	//결제
	@Transactional
	@PostMapping("/order/orderClass")
	public String orderClass(@Valid @ModelAttribute("newOrder") Order order, BindingResult result, @ModelAttribute("amount") String amount,
			@ModelAttribute("orderList") List<Class> orderList, HttpServletRequest request, SessionStatus status) {
		//삽입 , 참여자 수 증가, 장바구니에서 삭제
		if (result.hasErrors()) {
			return "thyme/Order";
		}
		
		order.setUser_id("user333");
		
		for(Class c : orderList) {
			order.setClass_id(c.getClass_id());
			/* orderFacade.insertOrder(order); */
			orderFacade.updateParticipant(c.getClass_id());
			Cart cart = new Cart(order.getUser_id(), c.getClass_id());
			classFacade.deleteCart(cart);
		}
		
		status.setComplete();
		return "thyme/SuccessOrder";
	}
	
	
}
