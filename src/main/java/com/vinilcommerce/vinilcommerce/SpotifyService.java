package com.vinilcommerce.vinilcommerce;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinilcommerce.model.Genre;
import com.vinilcommerce.model.Product;
import com.vinilcommerce.repository.ProductRepository;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;

@Service
public class SpotifyService {
	
	@Autowired
	private ProductRepository repository;
	@Autowired
	private SpotifyApi spotifyApi;
	
	
	public void initAlbums(String genre) throws SpotifyWebApiException, IOException {

		System.out.println(spotifyApi.getAccessToken());
		
		List<Product> products = getAlbums(genre);
		
		if(!products.isEmpty()) {
			System.out.println("Salvando " + products.size() + " produtos ....");
			repository.saveAll(products);
		}

	}

	private List<Product> getAlbums(String genre) throws IOException, SpotifyWebApiException {
		
		Genre genreSearched = Genre.getEnum(genre.toUpperCase());
		
		String jsonSpotify = spotifyApi.searchAlbums(genreSearched.name()).limit(50).build().getJson();
		//System.out.println(jsonSpotify);
		
		JSONArray jsonArray = new JSONArray("[" + spotifyApi.searchAlbums(genreSearched.name()).limit(50).build().getJson() + "]");
		//System.out.println(jsonArray.length());
		
		JSONObject albums = jsonArray.getJSONObject(0).getJSONObject("albums");
		//System.out.println(albums);
		
		List<Product> products = new ArrayList<>();
		
		albums.getJSONArray("items").toList().stream().forEach(item -> {
			
			Map itemMap = (Map) item;
			
			String album = (String) itemMap.get("name");
			List artists = (List) itemMap.get("artists");
			
			Map artist = (Map) artists.get(0);
			String artistName = (String) artist.get("name");
			
			/*System.out.println("*** Artist: " + artistName );
			System.out.println(itemMap.get("name"));*/
			
			BigDecimal price = generateRandomValue();
			
			Product product = new Product(album, artistName, genreSearched, price);
			
			products.add(product);
			
		});
		
		return products;
		
	}

	private BigDecimal generateRandomValue() {
		double random = (Math.random()*50)+20;
		BigDecimal price = new BigDecimal(random).setScale(2, RoundingMode.CEILING);
		return price;
	}

}
