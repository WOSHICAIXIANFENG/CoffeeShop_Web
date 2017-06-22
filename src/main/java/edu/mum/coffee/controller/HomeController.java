package edu.mum.coffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.rest.client.PersonRestClient;

@Controller
public class HomeController {
	@Autowired
	PersonRestClient personRestClient;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String getHome(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    if(name.equals("xianfeng")){
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
		return "index";
	}

	@GetMapping({"/secure"})
	public String securePage() {
		return "secure";
	}
}
