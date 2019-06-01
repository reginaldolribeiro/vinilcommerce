package com.vinilcommerce.vinilcommerce;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EcommerceController {

	@Autowired
	private SaleRepository saleRepository;
	
	@Autowired
	private ProductRepository productRepository;

	// *** TESTES ***

	@GetMapping
	public String index() {
		return "Index ecommerce controller alsdkljajklsdkjlfakjlsdjklajklçsf";
	}
	
	@GetMapping("/sale")
	public List<Sale> getAllSales() {
		return saleRepository.findAll();
//		List<ItemSale> itens = new ArrayList<>();
//		ItemSale venda1 = new ItemSale(BigDecimal.TEN,
//				new Product("King diamond", "Rock", new BigDecimal(50), new BigDecimal(10)));		
//		itens.add(venda1);
//
////		Sale sale = new Sale(new Client("Reginaldo", "reginaldolribeiro@gmail.com"), itens);
//		Sale sale = new Sale("Reginaldo", itens);
//		sale.getItens().add(venda1);
//		
//		return Arrays.asList(sale);
	}
	
	// *** FIM DOS TESTES ***
	

	/**
	 * Consultar o catálogo de discos de forma paginada, filtrando por gênero e ordenando de forma crescente pelo 
	 * nome do disco;
	 * 
	 * @return
	 */
	@GetMapping("/product/genre/{genre}")
	public List<Product> findProducts(@PathVariable String genre){
		return null;
	}
	
	/**
	 * Consulta o disco pelo seu identificador.
	 * @return
	 */
	@GetMapping("/product/{id}")
	public Product findProductById(@PathVariable Long id) {
		//return new Product("Them", "Metal", new BigDecimal(49.9), new BigDecimal(15));
		return productRepository.findById(id).orElse(null);
		
	}
	
	@GetMapping("/product")
	public List<Product> findProduct() {
		Product them = new Product("Them", "Metal", new BigDecimal(50));
		//productRepository.save(them);
		return productRepository.findAll();
	}
	
	@PostMapping("/product")
	public Product createProduct(@RequestBody Product product) {
		System.out.println(product);
		return productRepository.save(product);
	}
	
	@DeleteMapping("/product/{id}")
	public void delete(@PathVariable Long id) {
		productRepository.deleteById(id);
	}
	
	
	/**
	 * Consultar todas as vendas efetuadas de forma paginada, filtrando pelo range de datas (inicial e final) da venda e ordenando de forma 
	 * decrescente pela data da venda;
	 * @return
	 */
	@GetMapping("/sale/{ini}/{fim}")
	public List<Sale> findSalesByRangeDate(@PathVariable String ini, @PathVariable String fim){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate initialDate = LocalDate.parse(ini, formatter);
		LocalDate finalDate = LocalDate.parse(fim, formatter);
		
		List<Sale> sales = saleRepository.findAllByDataBetween(initialDate, finalDate);
		System.out.println(sales.size());
		return sales;
	}
	
	// 5. Consulta uma venda pelo seu identificador
	@GetMapping("/sale/{id}")
	public Sale findSaleById(@PathVariable Long id) {
		System.out.println("Sale " + id);
		return saleRepository.findById(id).orElse(null);
	}
	
	@PostMapping("/sale")
	public Sale createSale(@RequestBody Sale sale) {
		System.out.println(sale); 
		Long id = sale.getItens().get(0).getProduct().getId();
		Product product = productRepository.findById(id).get();
		System.out.println(product);
		
		
		ItemSale itemSale = new ItemSale(product);
		sale.setItens(new ArrayList<ItemSale>());
		
		sale.getItens().add(itemSale);
		itemSale.setSale(sale);
		itemSale.calculateCashback();
		
		sale.calculateCashback();
		
		return saleRepository.save(sale);
	}

}
