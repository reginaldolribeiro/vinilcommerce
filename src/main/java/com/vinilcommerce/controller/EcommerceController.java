package com.vinilcommerce.controller;

import java.time.LocalDate;

import com.vinilcommerce.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vinilcommerce.model.Customer;
import com.vinilcommerce.model.Genre;
import com.vinilcommerce.model.ItemSale;
import com.vinilcommerce.model.Product;
import com.vinilcommerce.model.Sale;
import com.vinilcommerce.repository.CustomerRepository;
import com.vinilcommerce.repository.ProductRepository;
import com.vinilcommerce.repository.SaleRepository;
import com.vinilcommerce.service.CashbackService;

@RestController
@RequestMapping("/api")
public class EcommerceController {

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private ProductRepository productRepository;

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

		System.out.print("**** PAgeable " + pageable);

		if(genre == null) {
			Page<Product> products = productRepository.findAll(pageable);
			if (products.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(products, HttpStatus.OK);
		}

		Genre genreSearched;
		try {
			genreSearched = Genre.getEnum(genre.toUpperCase());
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Page<Product> products = productRepository.findByGenreOrderByName(genreSearched, pageable);
		if (products == null || products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(products, HttpStatus.OK);

	}

	/**
	 * 2. Consulta o disco pelo seu identificador.
	 *
	 * @return
	 */
	@GetMapping("/album/{id}")
	public ResponseEntity<Product> findAlbumById(@PathVariable Long id) {
		Product product = productRepository.findById(id).orElse(null);
		if (product == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
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

		return new ResponseEntity<>(saleService.findSalesByRangeDate(start, end, pageable), HttpStatus.OK);

	}

	/**
	 * 4. Consulta uma venda pelo seu identificador
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/sale/{id}")
	public ResponseEntity<Sale> findSaleById(@PathVariable Long id) {
		return saleRepository.findById(id)
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
