package com.vinilcommerce.vinilcommerce;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinilcommerce.model.Genre;
import com.vinilcommerce.model.Product;
import com.vinilcommerce.repository.ProductRepository;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;

/**
 * Teste - EXCLUIR
 *
 */

@RestController
@RequestMapping("/spotify")
public class SpotifyController {

	@Autowired
	private SpotifyService service;
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("{genre}")
	public List<Product> getAlbum(@PathVariable String genre) throws SpotifyWebApiException, IOException {
		
		service.initAlbums(genre);
		return productRepository.findAll();
	}
	
}
