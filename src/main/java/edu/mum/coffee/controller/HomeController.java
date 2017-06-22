package edu.mum.coffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.rest.client.PersonRestClient;

@Controller
@SessionAttributes(value={"order", "person"})
public class HomeController {
	@Autowired
	PersonRestClient personRestClient;
	
	@RequestMapping(value={"/", "/index", "/home"}, method=RequestMethod.GET)
	public String getHome(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			String name = auth.getName();
		    if(name.equals("xianfeng")){
		    	model.addAttribute("person", personRestClient.getPerson(68));
		    } else if(name.equals("samuel")){
		    	model.addAttribute("person", personRestClient.getPerson(59));
		    } else {
		    	if (model.containsAttribute("person")) {
		    		//model.re
		    	}
		    }
		}
	    
		if(!model.containsAttribute("order")){
			model.addAttribute("order", new Order());
		}
		
		return "index";
	}
	
//	@GetMapping({"/", "/index", "/home"})
//	public String homePage() {
//		return "index";
//	}

	@GetMapping({"/secure"})
	public String securePage() {
		return "secure";
	}
}
