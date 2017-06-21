package edu.mum.coffee.rest.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import edu.mum.coffee.domain.Person;


@Service
public class PersonRestClient {
	
	@Autowired
	RestTemplate restTemplate;
	
	public void create(Person person){
		restTemplate.postForObject("http://localhost:8080/personRest/savePerson", person, Person.class);
	}
	
	public Person getPerson(long id){
		return restTemplate.getForObject("http://localhost:8080/personRest/getPerson/" + id, Person.class);
	}
	
	public List<Person> getAllPerson(){
		return Arrays.asList(restTemplate.getForObject("http://localhost:8080/personRest/getAllPerson", Person[].class));
	}
	
	public void update(Person person){
		restTemplate.postForObject("http://localhost:8080/personRest/savePerson", person, Person.class);
	}
	
	public void remove(Person person){
		restTemplate.postForObject("http://localhost:8080/personRest/removePerson", person, Person.class);
	}
}
