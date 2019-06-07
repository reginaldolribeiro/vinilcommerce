package com.vinilcommerce.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

@Entity
public class Sale {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	private LocalDate data = LocalDate.now();
	private String customer;
	private BigDecimal totalValue = BigDecimal.ZERO;
	private BigDecimal totalCashback = BigDecimal.ZERO;
	
	@OneToMany(mappedBy="sale", cascade=CascadeType.ALL)
	private List<ItemSale> itens = new ArrayList<ItemSale>();

	public Sale() {
	}
	
	public Sale(String customer, List<ItemSale> itens) {
		this.customer = customer;
		this.itens = itens;
	}
	
	
	public void calculateTotalCashback() {			
		itens.forEach(item -> {
			this.totalCashback = this.totalCashback.add(item.getCashbackValue());
			this.totalValue = this.totalValue.add(item.getPrice().plus());			
		});
		
	}
	
//	@PostPersist
//	public void prePersist() {
//		calculateCashback();
//		System.out.println("Salvando a venda do " + this.client + " em " + this.data);
//	}
	
	
//	public void calculateCashback() {
//		Cashback cashback = new Cashback();
////		this.itens.forEach(item -> {
////			BigDecimal cashbackProduct = cashback.calculate(item.getProduct(), this.data);
////			this.totalCashback = cashbackProduct.plus();
////			System.out.println(this.totalCashback);
////		});
//		
//		for (ItemSale item : itens) {
//			System.out.println(item);
//			BigDecimal cashbackProduct = cashback.calculate(item.getProduct(), this.data);
//			item.getProduct().setPrice(item.getProduct().getPrice().multiply(cashbackProduct));
//			this.totalCashback = cashbackProduct.plus();
//			System.out.println(this.totalCashback);
//			this.totalValue = item.getProduct().getPrice().plus();
//			System.out.println("Valor total:" + totalValue);
//		}
//	}
	
	
	
	

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

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
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
