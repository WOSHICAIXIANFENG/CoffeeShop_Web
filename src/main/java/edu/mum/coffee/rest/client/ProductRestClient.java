package edu.mum.coffee.rest.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.mum.coffee.domain.Product;

@Service
public class ProductRestClient{
	@Value("${rest_api_baseUrl}")
	private String REST_SERVICE_URI;
	
	@Autowired
	RestTemplate restTemplate;
	
	public List<Product> getAllProductList(){	
		Product[] products = restTemplate.getForObject(REST_SERVICE_URI + "/product", Product[].class);
		List<Product> prods = Arrays.asList(products);
		return prods;
	}
	
	public Product getProduct(int id){
		Product product = restTemplate.getForObject(REST_SERVICE_URI + "/product/" + id, Product.class);		
		return product;
	}
	
	public void createProduct(Product product){
		restTemplate.postForObject(REST_SERVICE_URI + "/product", product, Product.class);
	}
	
	public void updateProduct(Product product){
		restTemplate.put(REST_SERVICE_URI + "/product/" + product.getId(), product, Product.class);
	}
	
	public void removeProduct(Product product){
		restTemplate.delete(REST_SERVICE_URI + "/product/" + product.getId());
	}


}
