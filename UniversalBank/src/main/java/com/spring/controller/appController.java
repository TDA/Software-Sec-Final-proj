package com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dao.app;
import com.spring.service.appService;

@Controller
public class appController {
	
	private appService service;

@Autowired
public void setService(appService service) {
	this.service = service;
}

@RequestMapping(value="/app")
	public String showApp(Model model) {
	System.out.println("herecontroller1");
	List<app> apps=service.getCurrent();
	  model.addAttribute("sub1",apps);
		return "app";
	    
	}

@RequestMapping(value="/applist")
public String showAppList(Model model) {
System.out.println("herecontroller2");
model.addAttribute("sub2","jus");
	return "applist";
    
}
@RequestMapping(value="/loginsuccess",method=RequestMethod.POST)
public String showAppLogin(Model model,app App) {
System.out.println("herecontroller3");


	return "loginsuccess";
    
}
}
