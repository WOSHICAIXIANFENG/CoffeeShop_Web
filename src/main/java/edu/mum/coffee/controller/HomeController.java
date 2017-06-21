package edu.mum.coffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@Autowired
	PersonRestClient personRestClient;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String getHome(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    if(name.equals("yeerick")){
	    	model.addAttribute("person", personRestClient.getPerson(1));
	    } else if(name.equals("orlando")){
	    	model.addAttribute("person", personRestClient.getPerson(2));
	    }
		if(!model.containsAttribute("order")){
			model.addAttribute("order", new Order());
		}
		
		return "home";
	}
	
	@GetMapping({"/", "/index", "/home"})
	public String homePage() {
		return "home";
	}

	@GetMapping({"/secure"})
	public String securePage() {
		return "secure";
	}
}
