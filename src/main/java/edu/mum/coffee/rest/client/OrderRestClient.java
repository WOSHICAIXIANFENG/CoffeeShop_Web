package edu.mum.coffee.rest.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Person;


@Service
public class OrderRestClient {
	@Value("${rest_api_baseUrl}")
	private String REST_SERVICE_URI;

	@Autowired
	RestTemplate restTemplate;
	
	public List<Order> getAllOrder(){
		return Arrays.asList(restTemplate.getForObject(REST_SERVICE_URI + "http://localhost:8080/orderRest/getAllOrder", Order[].class));
	}
	
	public Order getOrder(int id){
		return restTemplate.getForObject("http://localhost:8080/orderRest/getOrder/"+ id, Order.class);
	}
	
	public List<Order> getOrderByPerson(Person person){
		return Arrays.asList(restTemplate.getForObject("http://localhost:8080/orderRest/getOrderByPerson/" + person.getId(), Order[].class));
	}
	
	public void createOrder(Order order){
		restTemplate.postForObject("http://localhost:8080/orderRest/saveOrder", order, Order.class);
	}
	
	
	
}
