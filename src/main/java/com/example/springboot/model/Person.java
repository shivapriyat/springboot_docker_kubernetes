package com.example.springboot.model;

import java.util.Comparator;

public class Person {

	String firstName;
	String lastName;
	int age;
	int id;
	
	
	public Person () {
		
	}
	
	public Person(String firstName, String lastName, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public Comparator<Integer> idComparator = new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			
			if(o1 == o2 )
				return 0;
			else return o2 > o1 ? 1  : -1;
		}
		
	};
	
}
