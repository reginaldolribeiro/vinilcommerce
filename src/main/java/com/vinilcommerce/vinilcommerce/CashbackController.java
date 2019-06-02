package com.vinilcommerce.vinilcommerce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cashback")
public class CashbackController {
	
	@Autowired
	private CashbackRepository repository;
	
	@GetMapping
	public List<Cashback> findAll(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cashback> findById(@PathVariable Long id) {
		System.out.println("Buscando pelo id " + id);
		Cashback cashback = repository.findById(id).orElse(null);
		if(cashback == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cashback>(cashback, HttpStatus.OK);
	}
	
	@PostMapping
	public Cashback createCashback(@RequestBody Cashback cashback) {
		return repository.save(cashback);
	}
	
}
