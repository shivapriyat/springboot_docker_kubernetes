package com.example.springboot.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.model.Person;
import com.example.springboot.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	private Map<Integer, Person> personMap = new HashMap<Integer, Person>();
	@Autowired
	PersonServiceImpl() {
		Person p1 = new Person("Shiva", "S", 1000 );
		p1.setId(1);
		Person p2 = new Person("Ganesha", "S", 900);
		p2.setId(2);
		this.personMap.put(p1.getId(), p1);
		this.personMap.put(p2.getId(), p2);
	}
	public List<Person> getAllPerson() {
		return new ArrayList<Person>(this.personMap.values());
	}
	@Override
	public Person addNewPerson(Person person) {
		 List<Person> pList = new ArrayList<Person>(this.personMap.values());
		List<Integer> pIds = pList.stream().map(p -> p.getId()).collect(Collectors.toList());
		pIds.sort(person.idComparator);
		int newId = pIds.get(0)+1;
		person.setId(newId);
		this.personMap.put(newId, person);
		return person;
	}
	@Override
	public boolean modifyPerson(Person person, Integer id) {
		if(this.personMap.get(id) != null ) {
			Person updatedPerson = new Person();
			updatedPerson.setId(id);
			if(person.getAge() != 0) {
				updatedPerson.setAge(person.getAge());
			}
			if(person.getFirstName() != null ) {
				updatedPerson.setFirstName(person.getFirstName());
			}
			if(person.getLastName() != null) {
				updatedPerson.setLastName(person.getLastName());
			}
			this.personMap.put(id, updatedPerson);
			return true;
		}
		return false;
	}
	@Override
	public boolean deletePerson(Integer id) {
		this.personMap.remove(id);
		return this.personMap.containsKey(id);
	}
	
	

}
