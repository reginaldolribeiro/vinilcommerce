package com.vinilcommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinilcommerce.model.Genre;
import com.vinilcommerce.model.Product;

@Repository
//@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

	//@RestResource(path="bygenre", rel="bygenre")
	Page<Product> findByGenreOrderByName(Genre genre, Pageable pageable);
	
}
