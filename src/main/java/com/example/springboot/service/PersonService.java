package com.example.springboot.service;

import java.util.List;

import com.example.springboot.model.Person;

public interface PersonService {

	public List<Person> getAllPerson();

	public Person addNewPerson(Person person);

	public boolean modifyPerson(Person person, Integer id);

	public boolean deletePerson(Integer id);

}
