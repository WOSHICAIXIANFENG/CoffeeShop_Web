package edu.mum.coffee.rest.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
	
	public Person getPersonByEmail(String email){
		HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    	MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
    	map.add("email", email);
    	HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

    	ResponseEntity<Person> response = restTemplate.postForEntity( REST_SERVICE_URI + "/personByEmail/", request, Person.class );
    	return response.getBody();
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
