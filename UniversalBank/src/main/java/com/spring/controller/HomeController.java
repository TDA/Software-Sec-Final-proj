package com.spring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.dao.app;

@Controller
public class HomeController {


@RequestMapping(value="/")
	public String showHome(Model model) {
	System.out.println("herecontroller");
	  model.addAttribute("sub","Welcometohome");
		return "home";
	    
	}
	
		
}
