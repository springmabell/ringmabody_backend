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
import com.example.jpetstore.domain.Foo;
import com.example.jpetstore.domain.PagingVO;
import com.example.jpetstore.domain.RequestModel;
import com.example.jpetstore.domain.User;


@Controller
@RequestMapping("/class")
@SessionAttributes("newClass")
public class ClassController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClassController.class);

	@Autowired
	private ClassFacade classFacade;

	@Autowired
	private SchedulerFacade schedulerFacade; // �����ٷ� ���� �������̽�

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

		return new String[] { "����", "���", "����", "�泲", "���", "����", "����", "�泲", "���", "�λ�", "�뱸", "��õ", "����", "����", "����",
				"���", "����" };

	}

	@ModelAttribute("filtering")
	public Filtering formBacking(HttpServletRequest request) throws Exception {
		return new Filtering();
	}

	@PostMapping("/viewList")
	public String filteringClass(@ModelAttribute(value = "filtering") Filtering filtering, Model model,
			HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setHeader("Expires", "0"); // Proxies.

		List<Class> classList = classFacade.filteringClass(filtering);

		model.addAttribute("classList", classList);

		return "thyme/viewList";
	}

	// Ŭ���� �Խ��� ���
	@GetMapping("/viewList")
	public String viewClassList(Model model, @RequestParam(required = false) String keyword,
			@RequestParam(required = false) String nowPage, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setHeader("Expires", "0"); // Proxies.

		int total = classFacade.countClass();
		int cntPerPage = 9;

		if (nowPage == null) {
			nowPage = "1";
		}

		Filtering filtering = new Filtering();
		List<String> checkedCategory = new ArrayList<String>();
		String checkedLocal = "dd";
		// value1 will be checked by default.
		checkedCategory.add("value1");
		filtering.setCheckedLocal(checkedLocal);
		filtering.setCheckedCategory(checkedCategory);
		model.addAttribute("filtering", filtering);

		PagingVO vo = new PagingVO(total, Integer.parseInt(nowPage), cntPerPage, keyword);

		List<Class> classList = classFacade.viewClassList(vo);
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

		model.addAttribute("paging", vo);
		model.addAttribute("classList", classList);

		return "thyme/viewList";
	}

	// viewList���� �̹��� Ŭ�� �� �׿� �ش��ϴ� detail view�� �̵�
	@GetMapping("/viewClass/{class_id}")
	public String viewClass(@PathVariable("class_id") int class_id, Model model) {
		Class findClass = classFacade.findClass(class_id);
		classFacade.plusHit(class_id);
		model.addAttribute("findClass", findClass);
		return "thyme/detail";
	}

	//Ŭ���� ����
	@GetMapping("/deleteClass")
	public String deleteClass(@RequestParam("class_id") int class_id) {
		classFacade.deleteClass(class_id);
		return "redirect:/class/viewList";
	}
	
	// Ŭ���� �� �ۼ� get
	@GetMapping("/writeClass")
	public String writeClass() {
		return "thyme/ClassForm";
	}

	// Ŭ���� �� �ۼ� post
	@PostMapping("/writeClass")
	public String insertClass(@Valid Class newClass, BindingResult result, SessionStatus status, HttpServletRequest request) {
		if (result.hasErrors()) {
			return "thyme/ClassForm";
		}

		MultipartFile mf = newClass.getReport();
		String savedName = uploadFile(mf, request);

		newClass.setImg(savedName);

		newClass.setTeacher_id("teacher001");
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String edate = format.format(newClass.getEdate());

		// ������ �����ϱ��� ��û�����ϰ� �Ϸ� �ں��� ��û��ư ��Ȱ��ȭ�� �����ϱ� ���� �ڵ�
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
//		uuid ����(Universal Unique IDentifier, ���� ���� �ĺ���)
		UUID uuid = UUID.randomUUID();
//		�������� + �����̸� ����
		String savedName = uuid.toString() + "_" + report.getOriginalFilename();
		/*
		 * String storagePath =
		 * "C:\\Users\\����\\Documents\\sosigae\\springmabell\\src\\main\\resources\\static\\images\\";
		 */
		String storagePath = request.getServletContext().getRealPath("resources/images/" + savedName);
//		�ӽõ��丮�� ����� ���ε�� ������ ������ ���丮�� ����
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