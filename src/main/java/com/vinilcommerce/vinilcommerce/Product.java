package com.vinilcommerce.vinilcommerce;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
	
	@Id
	private Long id;
	private String name;
	private String genre;
	private BigDecimal price;
	private BigDecimal cashback;
	
	public Product() {
	}

	public Product(String name, String genre, BigDecimal price, BigDecimal cashback) {
		this.name = name;
		this.genre = genre;
		this.price = price;
		this.cashback = cashback;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public BigDecimal getPrice() {
		if(this.genre.equalsIgnoreCase("ROCK")) {
//			return this.price.multiply(this.cashback.divide(new BigDecimal(100)));
			BigDecimal subtract = BigDecimal.ONE.subtract(this.cashback.divide(new BigDecimal(100)));
			return this.price.multiply(subtract);
		}
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getCashback() {
		return cashback;
	}

	public void setCashback(BigDecimal cashback) {
		this.cashback = cashback;
	}


}
