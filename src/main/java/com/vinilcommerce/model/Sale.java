package com.vinilcommerce.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate data = LocalDate.now();
	
	@OneToOne()
	private Customer customer;
	
	private BigDecimal totalValue = BigDecimal.ZERO;
	private BigDecimal totalCashback = BigDecimal.ZERO;

	@OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
	private List<ItemSale> itens = new ArrayList<ItemSale>();

	public Sale() {
	}

	public Sale(Customer customer, List<ItemSale> itens) {
		this.customer = customer;
		this.itens = itens;
	}

	public void calculateTotalCashback() {
		itens.forEach(item -> {
			this.totalCashback = this.totalCashback.add(item.getCashbackValue());
			this.totalValue = this.totalValue.add(item.getPrice().plus());
		});

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer	 customer) {
		this.customer = customer;
	}

	public List<ItemSale> getItens() {
		return itens;
	}

	public void setItens(List<ItemSale> itens) {
		this.itens = itens;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	public BigDecimal getTotalCashback() {
		return totalCashback;
	}

	public void setTotalCashback(BigDecimal totalCashback) {
		this.totalCashback = totalCashback;
	}

	@Override
	public String toString() {
		return "Sale [id=" + id + ", data=" + data + ", customer=" + customer + ", itens=" + itens + ", totalValue="
				+ totalValue + ", totalCashback=" + totalCashback + "]";
	}

}
