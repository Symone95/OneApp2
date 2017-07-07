package controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import db.DAO;

@Controller
public class ControllerEroi {

	@Autowired
	DAO dao;
	
	@RequestMapping("/")
	public String home(Map<String, Object> model){
		System.out.println("Cerco di restituire la home");
		return "home";
	}
	
	@RequestMapping("/home")
	public String home2(){
		System.out.println("Cerco di restituire la home");
		return "home";
	}
	
}