package edu.mum.coffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.rest.client.PersonRestClient;

@Controller
@SessionAttributes(value={"order", "person"})
public class PersonController {
	
	@Autowired
	PersonRestClient personRestClient;
	
	@RequestMapping(value="/addPerson", method = RequestMethod.POST)
	public String create(@ModelAttribute("person") Person person){
		personRestClient.create(person);
		
		return "redirect:/personlist";
	}
	
	@RequestMapping(value="/editmyprofile", method = RequestMethod.GET)
	public String getMyProfile(Model model, @ModelAttribute("person") Person person){
		model.addAttribute("pers", person);
		return "editperson";
	}
	
	@RequestMapping(value="/personlist", method = RequestMethod.GET)
	public String getAllPerson(Model model){
		model.addAttribute("persons", personRestClient.getAllPerson());	
		model.addAttribute("person", new Person());
		return "listPersons";
	}
	
	@RequestMapping(value="/editPerson/{id}", method = RequestMethod.GET)
	public String getEditPerson(@PathVariable long id, Model model){	
		model.addAttribute("person", personRestClient.getPerson(id));	
		System.out.println("Samuel Test PersonController person = " + personRestClient.getPerson(id));
		return "updatePerson";
	}
	
	@RequestMapping(value="/editPerson", method = RequestMethod.POST)
	public String editPerson(@ModelAttribute("person") Person person){
		personRestClient.update(person);
		
		return "redirect:/personlist";
	}
	
	@RequestMapping(value="/removePerson", method = RequestMethod.POST)
	public String removePerson(long id, Model model){
		personRestClient.remove(personRestClient.getPerson(id));
		return "redirect:/personlist";
	}
}
