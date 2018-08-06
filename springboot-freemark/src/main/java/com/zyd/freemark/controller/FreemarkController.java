package com.zyd.freemark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@Controller
public class FreemarkController {

	@RequestMapping(value = "/tt/{test}",method = RequestMethod.GET)
	public String index(@PathVariable("test") String test) {
		System.out.println(test);
		return "index";
	}

	@RequestMapping("/toUpload")
	public String toUpload(Model model) {
		return "upload";
	}

	@RequestMapping("/toFormdata")
	public String formdata(Model model) {
		return "formdata";
	}

	@RequestMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile[] files, String name,HttpServletRequest request) {
		if(files != null){
			for(MultipartFile file : files){
				System.out.println(file.getOriginalFilename());
			}
		}
		System.out.println(request.getParameter("name"));
		return name;
	}

	@RequestMapping("/formdata")
	@ResponseBody
	public String formdata(@RequestParam("file") MultipartFile[] files, String name,HttpServletRequest request) {
		if(files != null){
			for(MultipartFile file : files){
				System.out.println(file.getOriginalFilename());
			}
		}
		System.out.println(request.getParameter("name"));
		return name;
	}
}
