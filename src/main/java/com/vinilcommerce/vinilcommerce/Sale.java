package com.vinilcommerce.vinilcommerce;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Sale {

	@Id
	private Long id;
	private Date data = new Date();
	
	@OneToOne
	private Client client;
	
	@OneToMany(mappedBy="sale")
	private List<ItemSale> itens = new ArrayList<ItemSale>();

	public Sale() {
	}
	
	public Sale(Client client, List<ItemSale> itens) {
		this.client = client;
		this.itens = itens;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<ItemSale> getItens() {
		return itens;
	}

	public void setItens(List<ItemSale> itens) {
		this.itens = itens;
	}

}
