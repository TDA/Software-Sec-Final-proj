package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.app;
import com.spring.dao.appDAO;

@Service("offers service")
public class appService {
	
	private appDAO dao;
	
	@Autowired
	public void setDao(appDAO dao) {
		this.dao = dao;
	}

	public List<app>getCurrent(){
		return dao.getOffers();
	}

	
	
	
}
