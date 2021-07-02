package com.example.springboot.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.model.Person;
import com.example.springboot.service.PersonService;


@RestController
public class PersonController {

	@Autowired
	PersonService personService;
	
	/*@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}*/
	Logger log = Logger.getLogger("PersonController");
	
	@GetMapping("/persons")
	public ResponseEntity getPersons() {
		log.info("Request to get all persons");
		List<Person> personList = personService.getAllPerson(); 
		return ResponseEntity.status(HttpStatus.OK).body(personList);
	}
	
	@PostMapping("/persons")
	public ResponseEntity addPerson(@RequestBody Person person) {
		log.info("Request to add new person with name: "+person.getFirstName());
		Person p = personService.addNewPerson(person); 
		return ResponseEntity.status(HttpStatus.OK).body(p);
	}
	
	@PutMapping("/persons/{id}")
	public ResponseEntity updatePerson(@RequestBody Person person,@PathVariable Integer id) {
		log.info("Request to update person with name: "+person.getFirstName());
		boolean isUpdated = personService.modifyPerson(person,id); 
		if(isUpdated) {
			return ResponseEntity.status(HttpStatus.OK).body("{\"msg\": \"Updated Successfully\"}");
		}
		else {
			return ResponseEntity.status(412).body("{\"msg\": \"Update Failed\"}");
		}
	}
	@DeleteMapping("/persons/{id}")
	public ResponseEntity deletePerson(@PathVariable Integer id) {
		log.info("Request to delete person with id: "+ id );
		boolean isFound = personService.deletePerson(id); 
		if(!isFound) {
			return ResponseEntity.status(HttpStatus.OK).body("{\"msg\": \"Deleted Successfully\"}");
		}
		else {
			return ResponseEntity.status(412).body("{\"msg\": \"Delete Failed\"}");
		}
		
	}
}
