package com.vinilcommerce.vinilcommerce;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import com.vinilcommerce.model.Genre;
import com.vinilcommerce.model.ItemSale;
import com.vinilcommerce.model.Product;
import com.vinilcommerce.model.Sale;
import com.vinilcommerce.repository.ProductRepository;
import com.vinilcommerce.repository.SaleRepository;

@RestController
//@RequestMapping("/vinilcommerce/api")
public class EcommerceController {

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CashbackService cashbackService;

	/**
	 * 1. Consultar o catálogo de discos de forma paginada, filtrando por gênero e
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
	 * 2. Consulta o disco pelo seu identificador.
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
	
	/**
	 * 3. Consultar todas as vendas efetuadas de forma paginada, filtrando pelo range
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

	/**
	 * 4. Consulta uma venda pelo seu identificador
	 * @param id
	 * @return
	 */
	@GetMapping("/sale/{id}")
	public ResponseEntity<Sale> findSaleById(@PathVariable Long id) {
		System.out.println("Sale " + id);
		Sale sale = saleRepository.findById(id).orElse(null);
		if (sale == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Sale>(sale, HttpStatus.OK);
		
	}

	/**
	 * 5. Registrar uma nova venda de discos calculando o valor total de cashback considerando a tabela.
	 * @param sale
	 * @return
	 */
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

		sale.calculateTotalCashback();
		sale = saleRepository.save(sale);
		
		return new ResponseEntity<Sale>(sale, HttpStatus.CREATED);
	}
	
	
	/**
	 * 
	 * @return
	 */
	

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


}
