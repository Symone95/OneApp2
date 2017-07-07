package SourceSense.ProvaSpring.jsp;

import java.util.Date;
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
	public String welcome() {
		return "home";
	}
	
	@RequestMapping("/oneapp2")
	public String welcome2() {
		return "home";
	}
	
	
	
}