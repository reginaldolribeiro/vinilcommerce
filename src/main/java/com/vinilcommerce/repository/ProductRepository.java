package com.vinilcommerce.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.vinilcommerce.model.Genre;
import com.vinilcommerce.model.Product;

//@Repository
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

	@RestResource(path="bygenre", rel="bygenre")
	List<Product> findByGenreOrderByName(@Param("genre") Genre genre, Pageable p);
	
}
