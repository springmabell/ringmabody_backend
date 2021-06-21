package com.example.jpetstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.PagingVO;
import com.example.jpetstore.domain.Product;
import com.example.jpetstore.domain.TeacherAccount;
import com.example.jpetstore.domain.UserAccount;
import com.example.jpetstore.service.PetStoreFacade;

/**
 * @author Juergen Hoeller
 * @since 01.12.2003
 * @modified by Changsup Park
 */
@Controller
@SessionAttributes({"userSession","teacherSession"})
public class ListUsersController {

	private PetStoreFacade petStore;

	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}
	
	@RequestMapping("/admin/listUsers.do")
	public String boardList(PagingVO vo, Model model
			, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		
		List<UserAccount> userList = this.petStore.getAllUserAccount();
		int size_of_list = userList.size();
		
		model.addAttribute("userList", userList);
		model.addAttribute("size", size_of_list);
		
		int total = this.petStore.countUser();
		
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "10";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "10";
		}
		
		vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		model.addAttribute("paging", vo);
		model.addAttribute("viewAll", this.petStore.selectUser(vo));
		return "thyme/admin_user_list";
	}
	
	@RequestMapping("/admin/User/info.do")
	public String infoList(Model model
			, @RequestParam(value="user_id")String user_id) {
		
		UserAccount user = this.petStore.getUserAccount(user_id);
		
		model.addAttribute("user", user);
		
		return "thyme/admin_user_info_detail";
	}
	

	@RequestMapping("/admin/User/delete")
	public String deleteList(Model model
			, @RequestParam(value="user_id")String user_id) {
		
		this.petStore.deleteUser(user_id);
		
		return "thyme/admin_user_list";
	}



}
