package com.vinilcommerce.vinilcommerce;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CashbackRepository extends JpaRepository<Cashback, Long>{

	Cashback findByGenreAndDiaSemana(Genre genre, DiaSemana diaSemana);
	
}
