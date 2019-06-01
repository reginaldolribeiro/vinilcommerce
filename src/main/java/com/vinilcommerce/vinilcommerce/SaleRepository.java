package com.vinilcommerce.vinilcommerce;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	List<Sale> findAllByDataBetween(LocalDate initialDate, LocalDate finalDate);

}
