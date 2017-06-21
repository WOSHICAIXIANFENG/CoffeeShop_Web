package edu.mum.coffee.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.mum.coffee.domain.Address;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.rest.client.PersonRestClient;

@Controller
@SessionAttributes(value={"order", "person"})
public class PersonController {
	
	@Autowired
	PersonRestClient personRestClient;
	

	@RequestMapping(value="/addPerson", method = RequestMethod.GET)
	public String getCreate(){
		return "createperson";
	}
	
	@RequestMapping(value="/addPerson", method = RequestMethod.POST)
	public String create(String firstName, String lastName, String email, String phone, String city, String state, String zipcode,String country, String enable){
		Address newAdd = new Address(city, state, country, zipcode);
		Person newP = new Person(firstName,lastName, email, newAdd, phone, (enable.equals("1")? true : false));
		newP.setFirstName(firstName);
		newP.setLastName(lastName);
		newP.setEmail(email);
		newP.setPhone(phone);
		personRestClient.create(newP);
		
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
		return "personlist";
	}
	
	@RequestMapping(value="/editPerson", method = RequestMethod.GET)
	public String getEditPerson(long id, Model model){
		model.addAttribute("pers", personRestClient.getPerson(id));		
		return "editperson";
	}
	
	@RequestMapping(value="/editPerson", method = RequestMethod.POST)
	public String editPerson(long id, String firstName, String lastName, String email, String phone, String city, String state, String zipcode,String country, String enable){
		Person pers = personRestClient.getPerson(id);
		pers.setFirstName(firstName);
		pers.setLastName(lastName);
		pers.setEmail(email);
		pers.setPhone(phone);
		if(enable.equals("1")){
			pers.setEnable(true);
		} else {
			pers.setEnable(false);
		}
		Address address = new Address();
		if(pers.getAddress() != null){
			address.setId(pers.getAddress().getId());
		}
		address.setCity(city);
		address.setState(state);
		address.setCountry(country);
		address.setZipcode(zipcode);
		pers.setAddress(address);
		personRestClient.update(pers);
		
		return "redirect:/personlist";
	}
	
	@RequestMapping(value="/removePerson", method = RequestMethod.POST)
	public String removePerson(long id, Model model){
		personRestClient.remove(personRestClient.getPerson(id));
		return "redirect:/personlist";
	}
}
