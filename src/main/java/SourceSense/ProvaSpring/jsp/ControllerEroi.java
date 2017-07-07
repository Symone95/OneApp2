package SourceSense.ProvaSpring.jsp;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import db.DAO;
import entities.Entity;
import entities.Eroe;

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
	
	@RequestMapping("/listaEroi")
	public String listaEroi(Map<String, Object>model) {
		
		String json = "[";
		
		ArrayList<Entity> eroi = dao.load("eroe");
		
		for(Entity e : eroi)
			json += ((Eroe)e).toJson() + ",";
		
		json = json.substring(0, json.length());
		json += "]";
		
		model.put("dato", json);
		return "json";
	}
	

	
	
}