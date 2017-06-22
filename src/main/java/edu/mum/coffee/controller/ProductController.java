package edu.mum.coffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import edu.mum.coffee.rest.client.PersonRestClient;
import edu.mum.coffee.rest.client.ProductRestClient;

@Controller
@SessionAttributes(value={"order", "person"})
public class ProductController {
	
	@Autowired
	ProductRestClient productRestClient;
	
	@Autowired
	PersonRestClient personRestClient;
	
	@Bean
	public Product getEmptyProduct() {
		return new Product();
	}
	
	@RequestMapping(value="/productlist", method=RequestMethod.GET)
	public String getAllProductList(Model model){
		model.addAttribute("products", productRestClient.getAllProductList());
		model.addAttribute("product", new Product());
		model.addAttribute("productType", ProductType.values());
		return "listProduct";
	}
	
	@RequestMapping(value="/product/{id}", method=RequestMethod.GET)
	public String getProduct(@PathVariable("id") int id, Model model){		
		model.addAttribute("product", productRestClient.getProduct(id));
		return "index";
	}
	
	@RequestMapping(value="/editProduct/{id}", method=RequestMethod.GET)
	public String getEditProduct(@PathVariable int id, Model model){
		model.addAttribute("product", productRestClient.getProduct(id));
		model.addAttribute("productType", ProductType.values());
		return "updateProduct";
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
	
	@RequestMapping(value="/addProduct", method=RequestMethod.POST)
	public String createProduct(String productName, String description, double price, String productType){			
		Product newP = new Product(productName, description, price, ProductType.valueOf(productType));
		productRestClient.createProduct(newP);
		return "redirect:/productlist";
	}
	
	@RequestMapping(value="/removeProduct/{id}", method=RequestMethod.GET)
	public String removeProduct(@PathVariable int id){			
		Product oldP = productRestClient.getProduct(id);
		productRestClient.removeProduct(oldP);
		return "redirect:/productlist";
	}
	
}
