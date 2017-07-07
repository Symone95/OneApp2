package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import db.DAO;

@Controller
public class ControllerEroi {

	@Autowired
	DAO dao;
	
	@RequestMapping("/")
	public String home(){
		System.out.println("Cerco di restituire la home");
		return "home";
	}
	
	
	
	
}
