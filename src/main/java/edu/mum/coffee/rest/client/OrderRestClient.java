package edu.mum.coffee.rest.client;

import java.util.ArrayList;
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
		Order[] orders = restTemplate.getForObject(REST_SERVICE_URI + "/order/", Order[].class);
		if (orders != null)
			return Arrays.asList(orders);
		
		return new ArrayList<>();
	}
	
	public Order getOrder(int id){
		return restTemplate.getForObject(REST_SERVICE_URI + "/order/"+ id, Order.class);
	}
	
	public List<Order> getOrderByPerson(Person person){
		System.out.println("Samuel Test person = " + person);
		Order[] orders = restTemplate.getForObject(REST_SERVICE_URI + "/orderByPerson/" + person.getId(), Order[].class);
		if (orders != null)
			return Arrays.asList(orders);
		
		return new ArrayList<>();
	}
	
	public void createOrder(Order order){
		restTemplate.postForObject(REST_SERVICE_URI + "/order/", order, Order.class);
	}
	
	
	
}
