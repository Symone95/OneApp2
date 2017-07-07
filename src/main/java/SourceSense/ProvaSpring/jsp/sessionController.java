package SourceSense.ProvaSpring.jsp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import interfaces.IUtente;

@Controller
public class sessionController {

//	@Autowired
//	public IUtente utente;
	
	@RequestMapping(value = "/formNuovoUtente")
	public String formNuovoUtente
	(
			Map<String,Object> model
	)
	
	{
		System.out.println("Sono nel metodo formNuovoUtente");
//		model.put("utente", utente);
		System.out.println("Oggetto:" + model.get("utente"));
		return "formNuovoUtente";
	}
	
}
