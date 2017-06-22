package edu.mum.coffee.rest.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import edu.mum.coffee.domain.Person;


@Service
public class PersonRestClient {
	@Value("${rest_api_baseUrl}")
	private String REST_SERVICE_URI;
	
	@Autowired
	RestTemplate restTemplate;
	
	public void create(Person person){
		restTemplate.postForObject(REST_SERVICE_URI + "/person/", person, Person.class);
	}
	
	public Person getPerson(long id){
		return restTemplate.getForObject(REST_SERVICE_URI + "/person/" + id, Person.class);
	}
	
	public List<Person> getAllPerson(){
		return Arrays.asList(restTemplate.getForObject(REST_SERVICE_URI + "/person/", Person[].class));
	}
	
	public void update(Person person){
		restTemplate.put(REST_SERVICE_URI + "/person/" + person.getId(), person);
	}
	
	public void remove(Person person){
		restTemplate.delete(REST_SERVICE_URI + "/person/" + person.getId());
	}
}
