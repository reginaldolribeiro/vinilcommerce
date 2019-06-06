package com.vinilcommerce.repository;

import java.time.DayOfWeek;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinilcommerce.model.Cashback;
import com.vinilcommerce.model.Genre;

@Repository
public interface CashbackRepository extends JpaRepository<Cashback, Long>{

	Cashback findByGenreAndDayOfWeek(Genre genre, DayOfWeek dayOfWeek);
	
}
