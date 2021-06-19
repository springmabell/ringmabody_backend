package com.example.jpetstore.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.example.jpetstore.domain.Cart;
import com.example.jpetstore.domain.CartCommand;
import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Class;
import com.example.jpetstore.service.ClassFacade;
import com.example.jpetstore.service.SchedulerFacade;


@Controller
@RequestMapping
public class CartController {

	@Autowired
	private ClassFacade classFacade;

	//장바구니
	@PostMapping("/cart")
	@ResponseBody
	public HashMap<String, Integer> cartAdd(HttpServletRequest request,
			@RequestParam(required = false) int class_id) {

		System.out.println("ajax");
		
		String user_id = "user333";
		/* Class findClass = classFacade.findClass(class_id); */
		Cart cart = new Cart(user_id, class_id);

//		이미 사용자가 게시글을 담았는지 판별하기 위해 호출
		int count = classFacade.existCart(cart);
		
//		만약 담지 않았을 때
//		징바구니 개수가 증가하고, cart 테이블에 제품을 담은 userID와 게시글의 ID가 삽입됨
		if (count == 0) {
			classFacade.insertCartItem(cart);
		}
		
//		장바구니 수 가지고 오기
		/* int cartAdded = gpurchaseFacade.countCartByboardNum(boardNum); */
//		장바구니 개수 update
		/*
		 * gpurchase.setCartAdded(cartAdded);
		 * gpurchaseFacade.gpurchaseCartUpdate(gpurchase);
		 */
		/*
		 * gpurchase = gpurchaseFacade.getGpurchaseDetail(boardNum);
		 */
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		/*
		 * map.put("count", count); map.put("cartAdded", cartAdded);
		 */
		map.put("count", count);
		
		return map;
	}
	
	@GetMapping("/class/cartlist")
	public String viewCartList(Model model) {
		List<CartCommand> cartList = classFacade.findCartList("user333");
		model.addAttribute("cartList", cartList);
		return "thyme/cart";
	}
	
	@DeleteMapping("/cart/deleteCart/{class_id}")
	@ResponseBody
	public String deleteCart(@PathVariable("class_id") int class_id) {
		System.out.println("dele");
		String user_id = "user333";
		Cart cart = new Cart(user_id, class_id);

		classFacade.deleteCart(cart); 
		return "thyme/ddd";
	}
}
