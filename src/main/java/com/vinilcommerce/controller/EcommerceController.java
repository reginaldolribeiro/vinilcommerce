package com.vinilcommerce.controller;

import com.vinilcommerce.model.Product;
import com.vinilcommerce.model.Sale;
import com.vinilcommerce.service.ProductService;
import com.vinilcommerce.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class EcommerceController {

	@Autowired
	private ProductService productService;
	@Autowired
	private SaleService saleService;

	/**
	 * 1. Consultar o catálogo de discos de forma paginada, filtrando por gênero e
	 * ordenando de forma crescente pelo nome do disco;
	 *
	 * @return
	 */
	@GetMapping("/album")
	public ResponseEntity<Page<Product>> findAlbumsByGenre(@RequestParam(value = "genre", required = false) String genre,
														   Pageable pageable) {
		Page<Product> products;
		try{
			products = productService.findAlbumsByGenre(genre, pageable);
		} catch (IllegalArgumentException e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return !products.isEmpty() ? new ResponseEntity<>(products, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * 2. Consulta o disco pelo seu identificador.
	 *
	 * @return
	 */
	@GetMapping("/album/{id}")
	public ResponseEntity<Product> findAlbumById(@PathVariable Long id) {
		return productService.findAlbumById(id)
				.map(product -> new ResponseEntity<Product>(product, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * 3. Consultar todas as vendas efetuadas de forma paginada, filtrando pelo
	 * range de datas (inicial e final) da venda e ordenando de forma decrescente
	 * pela data da venda;
	 *
	 * @return
	 */
	@GetMapping("/sale")
	public ResponseEntity<Page<Sale>> findSalesByRangeDate(
			@RequestParam(value = "start", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate end,
			Pageable pageable) {

		Page<Sale> sales = saleService.findSalesByRangeDate(start, end, pageable);
		if (sales.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(sales, HttpStatus.OK);
	}

	/**
	 * 4. Consulta uma venda pelo seu identificador
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/sale/{id}")
	public ResponseEntity<Sale> findSaleById(@PathVariable Long id) {
		return saleService.findSaleById(id)
				.map(sale -> new ResponseEntity<Sale>(sale, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * 5. Registrar uma nova venda de discos calculando o valor total de cashback
	 * considerando a tabela.
	 *
	 * @param sale
	 * @return
	 */
	@PostMapping("/sale")
	public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
		sale = saleService.createSale(sale);
		if(sale == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<Sale>(sale, HttpStatus.CREATED);
	}

}
