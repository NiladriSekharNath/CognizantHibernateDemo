package com.cognizant.model;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {
	private int id;
	private String firstName;
	private String lastName;
	private Date date;
	
	public Employee() {
		
	}

	public Employee(String firstName, String lastName, Date date) {
		super();

		this.firstName = firstName;
		this.lastName = lastName;
		this.date = date;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", date=" + date + "]";
	}
	
	
}
