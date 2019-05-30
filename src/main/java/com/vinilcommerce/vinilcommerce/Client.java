package com.vinilcommerce.vinilcommerce;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client {

	@Id
	private Long id;
	private String name;
	private String email;

	public Client() {
	}
	
	
	
	public Client(String name, String email) {
		this.name = name;
		this.email = email;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
