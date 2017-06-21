package edu.mum.coffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes(value={"order", "person"})
public class ProductController {
	
	@Autowired
	ProductRestClient productRestClient;
	
	@Autowired
	PersonRestClient personRestClient;
	
	@RequestMapping(value="/productlist", method=RequestMethod.GET)
	public String getAllProductList(Model model){
		model.addAttribute("products", productRestClient.getAllProductList());
		return "productlist";
	}
	
	@RequestMapping(value="/product/{id}", method=RequestMethod.GET)
	public String getProduct(@PathVariable("id") int id, Model model){		
		model.addAttribute("product", productRestClient.getProduct(id));
		return "index";
	}
	
	@RequestMapping(value="/editProduct", method=RequestMethod.GET)
	public String getEditProduct(int id, Model model){
		model.addAttribute("product", productRestClient.getProduct(id));
		return "editproduct";
	}
	
	@RequestMapping(value="/editProduct", method=RequestMethod.POST)
	public String editProduct(int id, String productName, String description, double price, String productType){
		Product prod = productRestClient.getProduct(id);
		prod.setProductName(productName);
		prod.setDescription(description);
		prod.setPrice(price);
		prod.setProductType(ProductType.valueOf(productType));
		productRestClient.updateProduct(prod);
		return "redirect:/productlist";
	}
	
	
	@RequestMapping(value="/createProduct", method=RequestMethod.GET)
	public String getCreateProduct(){			
		return "createproduct";
	}
	
	@RequestMapping(value="/createProduct", method=RequestMethod.POST)
	public String createProduct(String productName, String description, double price, String productType){			
		Product newP = new Product(productName, description, price, ProductType.valueOf(productType));
		productRestClient.createProduct(newP);
		return "redirect:/productlist";
	}
	
	@RequestMapping(value="/removeProduct", method=RequestMethod.POST)
	public String removeProduct(int id){			
		Product oldP = productRestClient.getProduct(id);
		productRestClient.removeProduct(oldP);
		return "redirect:/productlist";
	}
	
}
