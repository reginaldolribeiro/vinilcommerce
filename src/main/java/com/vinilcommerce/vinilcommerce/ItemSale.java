package com.vinilcommerce.vinilcommerce;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ItemSale {
	
	@Id
	private Long id;
	private BigDecimal cashback;
	
	@OneToOne
	private Product product;
	
	@ManyToOne
	private Sale sale;
	
	public ItemSale(BigDecimal cashback, Product product) {
		this.cashback = cashback;
		this.product = product;
	}

	public ItemSale() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCashback() {
		return cashback;
	}

	public void setCashback(BigDecimal cashback) {
		this.cashback = cashback;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
