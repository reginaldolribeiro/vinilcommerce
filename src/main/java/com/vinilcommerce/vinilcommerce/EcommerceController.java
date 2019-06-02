package com.vinilcommerce.vinilcommerce;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@Autowired
	private CashbackService cashbackService;

	// *** TESTES ***

	@GetMapping("/sale1")
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
	 * Consultar o catálogo de discos de forma paginada, filtrando por gênero e
	 * ordenando de forma crescente pelo nome do disco;
	 * 
	 * @return
	 */
	@GetMapping("/product/genre/{genre}")
	public ResponseEntity<List<Product>> findProductsByGenre(@PathVariable String genre) {
		
		Genre genreSearched;
		try {
			genreSearched = Genre.valueOf(genre);			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		List<Product> products = productRepository.findByGenre(genreSearched);
		
		if (products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		
	}

	/**
	 * Consulta o disco pelo seu identificador.
	 * 
	 * @return
	 */
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> findProductById(@PathVariable Long id) {
		Product product = productRepository.findById(id).orElse(null);
		if (product == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@GetMapping("/product")
	public ResponseEntity<List<Product>> findProduct() {
		List<Product> products = productRepository.findAll();

		if (products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);

	}

	@PostMapping("/product")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		System.out.println(product);
		product = productRepository.save(product);
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		productRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/sale")
	public ResponseEntity<List<Sale>> findSales() {
		List<Sale> sales = saleRepository.findAll();
		if (sales.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Sale>>(sales, HttpStatus.OK);
	}

	/**
	 * Consultar todas as vendas efetuadas de forma paginada, filtrando pelo range
	 * de datas (inicial e final) da venda e ordenando de forma decrescente pela
	 * data da venda;
	 * 
	 * @return
	 */
	@GetMapping("/sale/{ini}/{fim}")
	public List<Sale> findSalesByRangeDate(@PathVariable String ini, @PathVariable String fim) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate initialDate = LocalDate.parse(ini, formatter);
		LocalDate finalDate = LocalDate.parse(fim, formatter);

		List<Sale> sales = saleRepository.findAllByDataBetween(initialDate, finalDate);
		System.out.println(sales.size());
		return sales;
	}

	// 5. Consulta uma venda pelo seu identificador
	@GetMapping("/sale/{id}")
	public ResponseEntity<Sale> findSaleById(@PathVariable Long id) {
		System.out.println("Sale " + id);
		Sale sale = saleRepository.findById(id).orElse(null);
		if (sale == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Sale>(sale, HttpStatus.OK);
		
	}

	
	@PostMapping("/sale")
	public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
		
		System.out.println(sale);
		for (ItemSale item : sale.getItens()) {
			
			Long idProduct = item.getProduct().getId();
			Product product = productRepository.findById(idProduct).orElse(null);
			
			if(product == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			item.setProduct(product);
			item.setSale(sale);
			item = cashbackService.calculate(item);
		}

		sale.calculateCashback();
		sale = saleRepository.save(sale);
		
		return new ResponseEntity<Sale>(sale, HttpStatus.CREATED);
	}
	
	
	// Inserindo apenas um item
	/*@PostMapping("/sale")
	public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
		System.out.println(sale);
		Long id = sale.getItens().get(0).getProduct().getId();
		Product product = productRepository.findById(id).get();
		System.out.println(product);

		ItemSale itemSale = new ItemSale(product);
		sale.setItens(new ArrayList<ItemSale>());

		sale.getItens().add(itemSale);
		itemSale.setSale(sale);
		// itemSale.calculateCashback();
		//BigDecimal cashbackPercentage = cashbackService.calculate(product, sale.getData());
		BigDecimal cashbackPercentage = cashbackService.getCashback(product, sale.getData());	
		itemSale = cashbackService.calculate(itemSale);
		//itemSale.calculateCashback(cashbackPercentage);

		sale.calculateCashback();
		sale = saleRepository.save(sale);
		
		return new ResponseEntity<Sale>(sale, HttpStatus.CREATED);
	}*/

}
