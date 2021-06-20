package com.example.jpetstore.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.jpetstore.service.ClassFacade;
import com.example.jpetstore.service.SchedulerFacade;
import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Class;
import com.example.jpetstore.domain.Filtering;
import com.example.jpetstore.domain.PagingVO;

import com.example.jpetstore.domain.PagingVO;
@Controller
@RequestMapping("/class")
@SessionAttributes("newClass")
public class ClassController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClassController.class);

	/*
	 * private static final Logger LOGGER =
	 * LoggerFactory.getLogger(ClassController.class);
	 */
	@Autowired
	private ClassFacade classFacade;

	@Autowired
	private SchedulerFacade schedulerFacade; // 스케줄러 서비스 인터페이스

	@ModelAttribute("newClass")
	public Class newClass() {
		Class newClass = new Class();
		return newClass;
	}

	@ModelAttribute("categoryList")
	public List<Category> getCategoryList() {
		List<Category> categoryList = classFacade.getCategoryList();
		return categoryList;
	}

	@ModelAttribute("localList")
	public String[] getLocalList() {
		return new String[] { "서울", "경기", "강원", "충남", "충북", "전남", "전북", "경남", "경북", "부산", "대구", "인천", "광주", "세종", "대전",
				"울산", "제주" };
	}

	@ModelAttribute("filtering")
	public Filtering formBacking(HttpServletRequest request) throws Exception {
		return new Filtering();
	}

	@PostMapping("/viewList")
	public String filteringClass(@ModelAttribute(value = "filtering") Filtering filtering, Model model,
			HttpServletResponse response, HttpSession session) {

		List<Class> classList = classFacade.filteringClass(filtering);
		
		if(session.getAttribute("userSession") != null){
			model.addAttribute("usertype", "user");
		} else if(session.getAttribute("teacherSession") != null){
			model.addAttribute("usertype", "teacher");
		}

		model.addAttribute("classList", classList);

		return "thyme/ViewClassList";
	}

	// 클래스 게시판 목록
	@GetMapping("/viewList")
	public String viewClassList(Model model, @RequestParam(required = false) String keyword,
		 HttpServletResponse response) {

		int total = classFacade.countClass();
		/* int cntPerPage = 9; */

		/*
		 * if (nowPage == null) { nowPage = "1"; }
		 */

		Filtering filtering = new Filtering();
		List<String> checkedCategory = new ArrayList<String>();
		String checkedLocal = "dd";
		// value1 will be checked by default.
		checkedCategory.add("value1");
		filtering.setCheckedLocal(checkedLocal);
		filtering.setCheckedCategory(checkedCategory);
		model.addAttribute("filtering", filtering);
		/*
		 * PagingVO vo = new PagingVO(total, Integer.parseInt(nowPage), cntPerPage,
		 * keyword);
		 */

		List<Class> classList = classFacade.viewClassList(keyword);
		Date today = new Date();
		long t = today.getTime();
		for (Class c : classList) {
			long e = c.getEdate().getTime();
			long cal = e - t;
			long calcDate = cal / (24 * 60 * 60 * 1000);
			if (calcDate > 0) {
				c.setDate(calcDate + 1);
			} else if (calcDate == 0) {
				c.setDate(0);
			} else {
				c.setDate(-1);
			}
		}

		/* model.addAttribute("paging", vo); */
		model.addAttribute("classList", classList);

		return "thyme/ViewClassList";
	}

	// viewList에서 이미지 클릭 시 그에 해당하는 detail view로 이동
	@GetMapping("/viewClass/{class_id}")
	public String viewClass(@PathVariable("class_id") int class_id, Model model) {
		Class findClass = classFacade.findClass(class_id);
		classFacade.plusHit(class_id);
		model.addAttribute("findClass", findClass);
		return "thyme/ViewDetailClass";
	}

	// 클래스 삭제
	@GetMapping("/deleteClass")
	public String deleteClass(@RequestParam("class_id") int class_id) {
		classFacade.deleteClass(class_id);
		return "redirect:/class/viewList";
	}

	// 클래스 폼 작성 get
	@GetMapping("/writeClass")
	public String writeClass() {
		return "thyme/ClassForm";
	}

	// 클래스 폼 작성 post
	@PostMapping("/writeClass")
	public String insertClass(@Valid Class newClass, BindingResult result, SessionStatus status,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			return "thyme/ClassForm";
		}

		MultipartFile mf = newClass.getReport();
		String savedName = uploadFile(mf, request);

		newClass.setImg(savedName);

		newClass.setTeacher_id("teacher001");
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String edate = format.format(newClass.getEdate());

		// 선택한 마감일까지 신청가능하고 하루 뒤부터 신청버튼 비활성화로 변경하기 위한 코드=
		try {
			Date eDate = format.parse(edate);
			Calendar c = Calendar.getInstance();
			c.setTime(eDate);
			c.add(Calendar.DATE, 1);
			eDate = c.getTime();
			newClass.setEdate(eDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		classFacade.writeClass(newClass);

		schedulerFacade.test(new Date());
		status.setComplete();

		return "thyme/finish";
	}

	private String uploadFile(MultipartFile report, HttpServletRequest request) {
		UUID uuid = UUID.randomUUID();

		String savedName = uuid.toString() + "_" + report.getOriginalFilename();

		String storagePath = request.getServletContext().getRealPath("resources/images/" + savedName);
		File file = new File(storagePath);
		try {
			report.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return savedName;
	}

	/*
	 * @PostMapping(value="/practice") public @ResponseBody String updateChkBox (
	 * HttpServletRequest request,
	 * 
	 * @RequestParam(value="name",required=true) List<String> name,
	 * 
	 * @RequestParam(value="age",required=true) List<Integer> age) throws Exception
	 * {
	 * 
	 * LOGGER.debug( ">>> param size : " + name.size() );
	 * 
	 * int i = 0; for( String value : name ){ LOGGER.debug( ">>> name's value : " +
	 * value + "\tage : " + age.get(i) ); i++; }
	 * 
	 * return "viewList"; }
	 */
}
