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

//
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

		String user_id = "user333";

		Cart cart = new Cart(user_id, class_id);

		int count = classFacade.existCart(cart);
				if (count == 0) {
			classFacade.insertCartItem(cart);
		}
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();

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
		return "ddd";
	}
}
