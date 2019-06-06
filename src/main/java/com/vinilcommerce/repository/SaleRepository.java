package com.vinilcommerce.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinilcommerce.model.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

	Page<Sale> findAllByDataBetweenOrderByDataDesc(LocalDate initialDate, LocalDate finalDate, Pageable pageable);

}
