package com.vinilcommerce.vinilcommerce;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String genre;
	private BigDecimal price;
	
	public Product() {
	}

	public Product(String name, String genre, BigDecimal price) {
		this.name = name;
		this.genre = genre;
		this.price = price;
	}

	
	/*public void calculateCashback(LocalDate data) {
	
	String diaDaSemana = data.getDayOfWeek().name();
	
	if(this.genre.equalsIgnoreCase("ROCK")) {
		
		if(diaDaSemana.equals("Domingo")) {
			BigDecimal subtract = BigDecimal.ONE.subtract(new BigDecimal(40).divide(new BigDecimal(100)));
			this.price.multiply(subtract);
		} else if(diaDaSemana.equals("Segunda-feira")) {
			BigDecimal subtract = BigDecimal.ONE.subtract(new BigDecimal(10).divide(new BigDecimal(100)));
			this.price.multiply(subtract);
		}			
	} else if(this.genre.equalsIgnoreCase("POP")) {
		
		if(diaDaSemana.equals("Domingo")) {
			BigDecimal subtract = BigDecimal.ONE.subtract(new BigDecimal(25).divide(new BigDecimal(100)));
			this.price.multiply(subtract);
		} else if(diaDaSemana.equals("Segunda-feira")) {
			BigDecimal subtract = BigDecimal.ONE.subtract(new BigDecimal(7).divide(new BigDecimal(100)));
			this.price.multiply(subtract);
		}
		
	}
}*/
	
	
	
	
	
	
	
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
//		if(this.genre.equalsIgnoreCase("ROCK")) {
//			return this.price.multiply(this.cashback.divide(new BigDecimal(100)));
//			BigDecimal subtract = BigDecimal.ONE.subtract(this.cashback.divide(new BigDecimal(100)));
//			return this.price.multiply(subtract);
//		}
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", genre=" + genre + ", price=" + price + "]";
	}


}
