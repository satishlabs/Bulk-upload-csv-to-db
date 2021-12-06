package com.estuate.csv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@Column(name = "empId",unique = true)
	private long empId;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "contact")
	private Long contact;
	
	@Column(name = "email")
	private String email;
	
	public Employee() {}

	public Employee(long empId, String firstname, String lastname, Long contact, String email) {
		this.empId = empId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.contact = contact;
		this.email = email;
	}
	public Employee(long id, long empId, String firstname, String lastname, Long contact, String email) {
		super();
		this.id = id;
		this.empId = empId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.contact = contact;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Long getContact() {
		return contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", empId=" + empId + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", contact=" + contact + ", email=" + email + "]";
	}

}
