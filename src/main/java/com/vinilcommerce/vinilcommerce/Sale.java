package com.vinilcommerce.vinilcommerce;

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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private LocalDate data = LocalDate.now();
	private String client;
	private BigDecimal totalValue = BigDecimal.ZERO;
	private BigDecimal totalCashback = BigDecimal.ZERO;
	
	@OneToMany(mappedBy="sale", cascade=CascadeType.ALL)
	private List<ItemSale> itens = new ArrayList<ItemSale>();

	public Sale() {
	}
	
	public Sale(String client, List<ItemSale> itens) {
		this.client = client;
		this.itens = itens;
	}
	
	
	public void calculateCashback() {			
		itens.forEach(item -> {
			this.totalCashback = item.getCashbackValue();
			System.out.println(this.totalCashback);
			this.totalValue = item.getPrice().plus();			
			System.out.println("Valor total:" + totalValue);
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

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
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
		return "Sale [id=" + id + ", data=" + data + ", client=" + client + ", itens=" + itens + ", totalValue="
				+ totalValue + ", totalCashback=" + totalCashback + "]";
	}

}
