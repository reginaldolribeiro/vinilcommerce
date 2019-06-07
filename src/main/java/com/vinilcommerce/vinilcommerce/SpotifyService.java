package com.vinilcommerce.vinilcommerce;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.vinilcommerce.model.Genre;
import com.vinilcommerce.model.Product;
import com.vinilcommerce.repository.ProductRepository;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

@Service
public class SpotifyService {

	/*@Value("${spotify.client-id}")
	private static String clientId;
	@Value("${spotify.client-secret}")
	private static String clientSecret;*/

	private static String clientId = "feda122d24cc485b904367f7480cf35f";
	private static String clientSecret = "7a8cdad98275410e8618b73ec13bb623";
	
	private static final SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId(clientId)
			.setClientSecret(clientSecret).build();
	
	private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();

	
	@Autowired
	private ProductRepository repository;
	
	
	public static void clientCredentials_Sync() {
		try {
			final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

			// Set access token for further "spotifyApi" object usage
			spotifyApi.setAccessToken(clientCredentials.getAccessToken());

			System.out.println("Expires in: " + clientCredentials.getExpiresIn());
		} catch (IOException | SpotifyWebApiException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	

	public void initAlbumsSpotify(String genre) throws SpotifyWebApiException, IOException {

		clientCredentials_Sync();
		
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
			
			System.out.println("*** Artist: " + artistName );
			System.out.println(itemMap.get("name"));
			
			BigDecimal price = BigDecimal.ZERO;
			Product product = new Product(album, artistName, genreSearched, price);
			System.out.println(product.toString());
			
			products.add(product);
			
		});
		return products;
	}
	
	
	/*public String getAlbums() throws SpotifyWebApiException, IOException {

		SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId(clientId).setClientSecret(clientSecret).build();
		ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
		ClientCredentials clientCredentials = clientCredentialsRequest.execute();

		spotifyApi.setAccessToken(clientCredentials.getAccessToken());

		System.out.println("Expires in: " + clientCredentials.getExpiresIn());

		System.out.println("***** TOKEN: " + spotifyApi.getAccessToken());

		clientCredentials_Sync();
		
		String json = spotifyApi.searchAlbums("rock").limit(5).build().getJson();
		System.out.println(json);

		return json;
	}*/

}
