package com.vinilcommerce.vinilcommerce;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemSale {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private BigDecimal cashbackPercentage;
	private BigDecimal price = BigDecimal.ZERO;
	private BigDecimal cashbackValue = BigDecimal.ZERO;

	@OneToOne(cascade=CascadeType.ALL)
	private Product product;
	
	@JsonIgnore
	@ManyToOne
	private Sale sale;
	
	public ItemSale(Product product) {
		this.product = product;
	}

	public ItemSale() {
	}

	/*
	 * Calculate cashback for item
	 * 
	 */
	public void calculateCashback() {
		this.cashbackPercentage = new Cashback().calculate(this.product, this.sale.getData());
		
		BigDecimal discount = BigDecimal.ONE.subtract(this.cashbackPercentage.divide(new BigDecimal(100)));
		this.price = this.getProduct().getPrice().multiply(discount);
		
		this.cashbackValue = this.product.getPrice().subtract(this.price);
	}
	
	/*@PrePersist
	public void prePersist() {
		calculateCashback();
		System.out.println("Salvando o item " + this.product.getName());
	}*/
	
	
//	public void calculateCashback() {
//		this.cashbackPercentage = new Cashback().calculate(this.product, this.sale.getData());
//		this.price = this.getProduct().getPrice().multiply(this.cashbackPercentage);
//		this.savedValue = this.product.getPrice().subtract(this.price);
//	}
	
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
