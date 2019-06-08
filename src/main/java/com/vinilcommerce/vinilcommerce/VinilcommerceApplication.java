package com.vinilcommerce.vinilcommerce;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.vinilcommerce.model.Genre;
import com.vinilcommerce.model.Product;
import com.vinilcommerce.repository.ProductRepository;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan("com.vinilcommerce.model")
@EnableJpaRepositories("com.vinilcommerce.repository")
public class VinilcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VinilcommerceApplication.class, args);
	}

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private SpotifyService spotifyService;

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			loadAlbumsSpotify();
		};
	}

	private void loadAlbumsSpotify() {
		
		Long count = productRepository.count();
		
		System.out.println("Albums quantity: " + count);

		if (count == 0) {
			List<Genre> genres = Arrays.asList(Genre.values());
			genres.stream().forEach(genre -> {
				String genreString = genre.name();
				try {
					spotifyService.initAlbums(genreString);
				} catch (SpotifyWebApiException | IOException e) {
					e.printStackTrace();
				}
			});
		}
	}

}
