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
		long count = productRepository.count();
		System.out.println("Qtd albums: " + count);

		if (count != 200) {
			List<Genre> genres = Arrays.asList(Genre.values());

			genres.stream().forEach(genre -> {
				String genreString = genre.name();
				// System.out.println(genreString);
				try {
					spotifyService.initAlbumsSpotify(genreString);
				} catch (SpotifyWebApiException | IOException e) {
					e.printStackTrace();
				}
			});
		}
	}

//	@Bean
//	public CommandLineRunner commandLineRunner() {
//		return args -> {
//			System.out.println("Inserindo...");
//			Product them = new Product("Them", "Metal", new BigDecimal(50), BigDecimal.TEN);
//			
//			System.out.println(them);
//		};
//	}

}
