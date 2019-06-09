package com.vinilcommerce.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemSale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal cashbackPercentage;
	private BigDecimal price = BigDecimal.ZERO;
	private BigDecimal cashbackValue = BigDecimal.ZERO;

	@OneToOne(cascade=CascadeType.ALL)
	private Product product;
	
	@JsonIgnore
	@ManyToOne
	private Sale sale;
	
	public ItemSale() {
	}
	
	public ItemSale(Product product) {
		this.product = product;
	}
	
	public BigDecimal getCashbackPercentage() {
		return cashbackPercentage;
	}

	public void setCashbackPercentage(BigDecimal cashbackPercentage) {
		this.cashbackPercentage = cashbackPercentage;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getCashbackValue() {
		return cashbackValue;
	}

	public void setCashbackValue(BigDecimal cashbackValue) {
		this.cashbackValue = cashbackValue;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "ItemSale [id=" + id + ", cashbackPercentage=" + cashbackPercentage + ", product=" + product + ", price="
				+ price + ", savedValue=" + cashbackValue + "]";
	}

}
