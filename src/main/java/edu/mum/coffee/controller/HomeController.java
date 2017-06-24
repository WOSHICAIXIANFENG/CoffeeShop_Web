package edu.mum.coffee.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.UserDto;
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
		    	if (name.contains("@")) {
		    		Person dest = personRestClient.getPersonByEmail(auth.getName());
		    		if (dest != null) {
		    			model.addAttribute("person", dest);
		    		} else {
		    			model.addAttribute("person", personRestClient.getPerson(74));
		    		}
		    	} else {
		    		model.addAttribute("person", personRestClient.getPerson(74));
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
	
	@GetMapping({"/register"})
	public String registerPage(Model model) {
		UserDto userDto = new UserDto();
	    model.addAttribute("user", userDto);
		return "register";
	}
	
	@Autowired
	private InMemoryUserDetailsManager manager;
	
	@PostMapping({"/register"})
	public String registerUser(@ModelAttribute("user") UserDto accountDto, 
			  BindingResult result, WebRequest request, Errors errors) {
		SimpleGrantedAuthority authUser = new SimpleGrantedAuthority("ROLE_USER");
		List<SimpleGrantedAuthority> auths1 = new ArrayList<>();
		auths1.add(authUser);
		
		try {
			User registered = new User(accountDto.getEmail(), accountDto.getPassword(), auths1);
			manager.createUser(registered);
		} catch (Exception e) {
			e.printStackTrace();
		}    
		return "index";
	}
}
