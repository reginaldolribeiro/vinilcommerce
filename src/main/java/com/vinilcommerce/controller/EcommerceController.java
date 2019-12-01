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

	@GetMapping("/album/{id}")
	public ResponseEntity<Product> findAlbumById(@PathVariable Long id) {
		return productService.findAlbumById(id)
				.map(product -> new ResponseEntity<Product>(product, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/sale")
	public ResponseEntity<Page<Sale>> findSalesByRangeDate(
			@RequestParam(value = "start", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate end,
			Pageable pageable) {

		Page<Sale> sales = saleService.findSalesByRangeDate(start, end, pageable);
		return !sales.isEmpty() ? new ResponseEntity<>(sales, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/sale/{id}")
	public ResponseEntity<Sale> findSaleById(@PathVariable Long id) {
		return saleService.findSaleById(id)
				.map(sale -> new ResponseEntity<Sale>(sale, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/sale")
	public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
		sale = saleService.createSale(sale);
		return (sale == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) : new ResponseEntity<Sale>(sale, HttpStatus.CREATED);
	}

}
