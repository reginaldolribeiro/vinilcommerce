package com.vinilcommerce.vinilcommerce;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

@Configuration
public class SpotifyConfiguration {

	private static String clientId = "feda122d24cc485b904367f7480cf35f";
	private static String clientSecret = "7a8cdad98275410e8618b73ec13bb623";

	private static final SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId(clientId)
			.setClientSecret(clientSecret).build();

	private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();

	@Autowired
	private SpotifyService spotifyService;

	@Bean
	public SpotifyApi clientCredentials() {

		try {
			final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

			// Set access token for further "spotifyApi" object usage
			spotifyApi.setAccessToken(clientCredentials.getAccessToken());

			System.out.println("Expires in: " + clientCredentials.getExpiresIn());

		} catch (IOException | SpotifyWebApiException e) {
			System.out.println("Error: " + e.getMessage());
		}

		return spotifyApi;

	}

	@Bean
	public void loadAlbums() {
		spotifyService.loadAlbums();
	}

	public static void clientCredentials_Async() {
		try {
			final Future<ClientCredentials> clientCredentialsFuture = clientCredentialsRequest.executeAsync();

			// ...

			final ClientCredentials clientCredentials = clientCredentialsFuture.get();

			// Set access token for further "spotifyApi" object usage
			spotifyApi.setAccessToken(clientCredentials.getAccessToken());

			System.out.println("Expires in: " + clientCredentials.getExpiresIn());
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Error: " + e.getCause().getMessage());
		}
	}

}
