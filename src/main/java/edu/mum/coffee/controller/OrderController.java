package edu.mum.coffee.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Orderline;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.rest.client.OrderRestClient;
import edu.mum.coffee.rest.client.PersonRestClient;
import edu.mum.coffee.rest.client.ProductRestClient;


@Controller
@SessionAttributes(value={"order", "person"})
public class OrderController {
	
	@Autowired
	OrderRestClient orderRestClient;
	
	@Autowired
	PersonRestClient personRestClient;
	
	@Autowired
	ProductRestClient productRestClient;
	
	@Bean
	public Order getEmptyOrder() {
		return new Order();
	}
	
	@RequestMapping(value="/orderlist", method= RequestMethod.GET)
	public String getAllOrder(Model model){
		model.addAttribute("orders", orderRestClient.getAllOrder());
		model.addAttribute("order", new Order());
		model.addAttribute("person", new Person());
		return "listOrders";
	}
	
	@RequestMapping(value="/currentorder", method= RequestMethod.GET)
	public String getCurrentOrder(){		
		return "currentorder";
	}
	
	@RequestMapping(value="/refreshorder", method= RequestMethod.POST)
	public String refreshCurrentOrder(Model model){	
		model.addAttribute("order", new Order());
		return "redirect:/currentorder";
	}
	
	@RequestMapping(value="/myorder", method=RequestMethod.GET)
	public String getMyOrder(Model model, @ModelAttribute("person") Person person){
		model.addAttribute("orders", orderRestClient.getOrderByPerson(person));
		model.addAttribute("myorder", "My");
		return "listOrders";
	}
	
	@RequestMapping(value="/addOrderline", method = RequestMethod.POST)
	public String addOrderline(int productId, int quantity, Model model, @ModelAttribute("order") Order order, @ModelAttribute("person") Person person){
		boolean inside = false;
		for(Orderline ol: order.getOrderLines()){
			if(ol.getProduct().getId() == productId){
				ol.setQuantity(ol.getQuantity() + quantity);
				inside = true;
			}
		}
		if(inside == false){
			order.addOrderLine(new Orderline(quantity, productRestClient.getProduct(productId)));
		}
		model.addAttribute("order", order);
		return "redirect:/productlist";
	}
	
	@RequestMapping(value="/addOrder", method= RequestMethod.POST)
	public String addOrder(Model model, @ModelAttribute("order") Order order){
		System.out.println("Samuel Test order = " + order);
		orderRestClient.createOrder(order);
		model.addAttribute("order", new Order());
		
		return "redirect:/orderlist";
	}
}
