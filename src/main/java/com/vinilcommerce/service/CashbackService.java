package com.vinilcommerce.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinilcommerce.model.Cashback;
import com.vinilcommerce.model.ItemSale;
import com.vinilcommerce.model.Product;
import com.vinilcommerce.repository.CashbackRepository;

@Service
public class CashbackService {

	@Autowired
	private CashbackRepository repository;
	
	public ItemSale calculate(ItemSale itemSale) {

		BigDecimal cashbackPercentage = getCashback(itemSale.getProduct(), itemSale.getSale().getData());
		
		itemSale.setCashbackPercentage(cashbackPercentage);
		
		BigDecimal discount = BigDecimal.ONE.subtract(cashbackPercentage.divide(new BigDecimal(100)));
		
		itemSale.setPrice(itemSale.getProduct().getPrice().multiply(discount));
		itemSale.setCashbackValue(itemSale.getProduct().getPrice().subtract(itemSale.getPrice()));
		
		return itemSale;
		
	}

	private BigDecimal getCashback(Product product, LocalDate data) {
		try {
			Cashback cashback = repository.findByGenreAndDayOfWeek(product.getGenre(), data.getDayOfWeek());			
			return cashback.getValue();
		} catch (NoResultException e) {
			return BigDecimal.ZERO;
		}
	}	
	
}
