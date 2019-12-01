package com.vinilcommerce.service;

import com.vinilcommerce.model.Genre;
import com.vinilcommerce.model.Product;
import com.vinilcommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> findAlbumById(Long id) {
        return productRepository.findById(id);
    }

    public Page<Product> findAlbumsByGenre(String genre, Pageable pageable) {
        if(genre == null)
            return productRepository.findAll(pageable);

        try {
            Genre genreSearched = Genre.getEnum(genre.toUpperCase());
            return productRepository.findByGenreOrderByName(genreSearched, pageable);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

}
