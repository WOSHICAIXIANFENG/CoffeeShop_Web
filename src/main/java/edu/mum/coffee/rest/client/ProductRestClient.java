package edu.mum.coffee.rest.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.mum.coffee.domain.Product;

@Service
public class ProductRestClient{
	
	@Autowired
	RestTemplate restTemplate;
	
	public List<Product> getAllProductList(){	
		Product[] products = restTemplate.getForObject("http://localhost:8080/productRest/getAllProduct", Product[].class);
		List<Product> prods = Arrays.asList(products);
		return prods;
	}
	
	public Product getProduct(int id){
		Product product = restTemplate.getForObject("http://localhost:8080/productRest/getProduct/" + id, Product.class);		
		return product;
	}
	
	public void createProduct(Product product){
		restTemplate.postForObject("http://localhost:8080/productRest/saveProduct", product, Product.class);
	}
	
	public void updateProduct(Product product){
		restTemplate.postForObject("http://localhost:8080/productRest/saveProduct", product, Product.class);
	}
	
	public void removeProduct(Product product){
		restTemplate.postForObject("http://localhost:8080/productRest/removeProduct", product, Product.class);
	}


}
