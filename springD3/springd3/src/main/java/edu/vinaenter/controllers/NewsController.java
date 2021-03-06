package edu.vinaenter.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.vinaenter.daos.NewsDao;
import edu.vinaenter.models.News;

@Controller
@RequestMapping("news") // line 29: needn't news/list, news/detail in getmapping
public class NewsController {

	@Autowired // DI
	private NewsDao newsDAO;
	private static final String MSG_ERR = "Không tìm thấy id,vui lòng đọc tin khác";
	private static final String MSG_SUCCESS = "Thêm tin thành công";
	private static final String DIR_UPLOAD = "uploads";	//set folder name: upload

	private List<News> listNews = new ArrayList<News>();

	@GetMapping("list")
	public String list(Model model) {
//		model.addAttribute("datas", listNews);	 Không cần vì có session
		return "news/list";
	}

//	@GetMapping("detail")
//	public String detail(@RequestParam  int id, Model model) {	
//													//, defaultValue="0": mặc định id=0, required=true: phải có id, nếu ko có id->lỗi
//												//nếu muốn rút gọn: bỏ value="id" nhưng vẫn giữ int id và in ra dòng 32:id			
//		News findNews = new News();
//		for (News news : newsDAO.getList()) {
//			if(news.getId() == id) {
//				findNews =  news;
//				break;	
//			}
//		}
//		model.addAttribute("news", findNews);
//		return "news/detail";
//	}
	@GetMapping("detail/{id}/{author}") // news/detail/1/jonnyTran
	public String detail(@PathVariable(name = "id") String id, HttpSession session, @PathVariable String author,
			Model model, RedirectAttributes re) { // dùng /{id(tự đặt)} -> đổi @RequestParam = @PathVariable
		// , defaultValue="0": mặc định id=0, required=true: phải có id, nếu ko có
		// id->lỗi
		// nếu muốn rút gọn: bỏ value="id" nhưng vẫn giữ int id và in ra dòng 32:id

		News findNews = null;
		if (session.getAttribute("datas") != null) {
			List<News> listData = (List<News>) session.getAttribute("datas");
			for (News news : listData) {
				if (news.getId().equals(id)) {
					findNews = news;
					break;
				}
			}
			if (findNews == null) {
				re.addFlashAttribute("msg", MSG_ERR);		//addFlash : sau khi hiển thị, reload trang => mất dữ liệu
				return "redirect:/news/list";
			}
		}

		model.addAttribute("news", findNews);
		return "news/detail";
	}

	@GetMapping("add")
	public String add() {
		return "news/add";
	}

	@PostMapping("add")
	public String add(Mode model, @ModelAttribute News news, HttpSession session, RedirectAttributes re, HttpServletRequest request) throws IllegalStateException, IOException { 
//		news.getTitle();
//		news.getStatus();
//		news.getDetail();
//		news.getAuthor();
		
		// uploadfile to server
		MultipartFile infoFile = news.getPic(); // get file information
		String fileName = getFileNameServer(infoFile.getOriginalFilename()); // to get file name with extention and path :myfile/.../../pic1.png
			System.out.println("tên file: " + fileName);
		infoFile.transferTo(pathFile(fileName, DIR_UPLOAD, request));
		news.setId(UUID.randomUUID().toString());
		news.setCreatedBy(new Date());
		news.setPicDes(fileName);
		listNews.add(news);
		session.setAttribute("datas", listNews);
		re.addFlashAttribute("msg", MSG_SUCCESS);
		return "redirect:/news/list";
	}

	public String getFileNameServer(String fileName) {
		// file name, null, check empty
		if (!StringUtils.isEmpty(fileName)) {
			String extention = FilenameUtils.getExtension(fileName); // to get file extention , ex: png, jpg,...
			String baseName = FilenameUtils.getBaseName(fileName); // file name without extention and path  ex: 1
			StringBuilder builder = new StringBuilder(); // to concatenate String
			builder.append(baseName).append("-").append(System.nanoTime()).append(".").append(extention); // đặt lại tên theo cấu trúc để ko trùng tên 
			return builder.toString();
		}
		return StringUtils.EMPTY;
	}
	public File pathFile(String fileName, String folder, HttpServletRequest request) {
		String rootPath = request.getServletContext().getRealPath("");	//trả về đường dẫn tuyệt đối web
		File disry = new File(rootPath + folder);	// đường dẫn folder: đường dẫn tuyệt đối=> cần httpservlet request
		if(!disry.exists()) {	// chưa tồn tại file=> tạo
			disry.mkdirs();
		}
		File file = new File(rootPath + folder + "/" + fileName);
		System.out.println("file Path: "  +  rootPath + folder + "/"
				+ ""
				+ "" + fileName);
		return file;
	}
	

}
