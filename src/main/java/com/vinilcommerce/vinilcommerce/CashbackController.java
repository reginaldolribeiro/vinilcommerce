package com.vinilcommerce.vinilcommerce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@PostMapping
	public Cashback createCashback(@RequestBody Cashback cashback) {
		return repository.save(cashback);
	}
	
}
