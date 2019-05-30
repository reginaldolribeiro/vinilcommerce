package com.vinilcommerce.vinilcommerce;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EcommerceController {

	@Autowired
	private SaleRepository saleRepository;

	/**
	 * 1. Consultar o catálogo de discos de forma paginada, filtrando por gênero e
	 * ordenando de forma crescente pelo nome do disco; 2. Consultar o disco pelo
	 * seu identificador; 3. Consultar todas as vendas efetuadas de forma paginada,
	 * filtrando pelo range de datas (inicial e final) da venda e ordenando de forma
	 * decrescente pela data da venda; 4. Consultar uma venda pelo seu
	 * identificador; 5. Registrar uma nova venda de discos calculando o valor total
	 * de cashback considerando a tabela.
	 * 
	 * @return
	 * 
	 */

	@GetMapping
	public String index() {
		return "Index ecommerce controller alsdkljajklsdkjlfakjlsdjklajklçsf";
	}

	@GetMapping("/sale")
	public List<Sale> getAllSales() {

		List<ItemSale> itens = new ArrayList<>();
		ItemSale venda1 = new ItemSale(BigDecimal.TEN,
				new Product("King diamond", "Rock", new BigDecimal(50), new BigDecimal(10)));		
		itens.add(venda1);

		Sale sale = new Sale(new Client("Reginaldo", "reginaldolribeiro@gmail.com"), itens);
		sale.getItens().add(venda1);
		
		return Arrays.asList(sale);
	}

	@GetMapping("/sale/{id}")
	public Sale findSaleById(@PathVariable Long id) {
		System.out.println("Sale " + id);
		return saleRepository.findById(id).orElse(null);
	}

}
