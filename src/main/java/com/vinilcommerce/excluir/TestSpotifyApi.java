package com.vinilcommerce.excluir;

import java.io.IOException;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

public class TestSpotifyApi {

	public static void main(String[] args) throws SpotifyWebApiException, IOException {

		String clientId = "feda122d24cc485b904367f7480cf35f";
		String clientSecret = "7a8cdad98275410e8618b73ec13bb623";

		SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId(clientId).setClientSecret(clientSecret).build();

		ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
		ClientCredentials clientCredentials = clientCredentialsRequest.execute();

		spotifyApi.setAccessToken(clientCredentials.getAccessToken());

		System.out.println("Expires in: " + clientCredentials.getExpiresIn());
		
		System.out.println("***** TOKEN: " + spotifyApi.getAccessToken());

		String json = spotifyApi.searchAlbums("rock").limit(5).build().getJson();
		System.out.println(json);

		/*
		 * public static void clientCredentials_Async() { try { final
		 * Future<ClientCredentials> clientCredentialsFuture =
		 * clientCredentialsRequest.executeAsync();
		 * 
		 * // ...
		 * 
		 * final ClientCredentials clientCredentials = clientCredentialsFuture.get();
		 * 
		 * // Set access token for further "spotifyApi" object usage
		 * spotifyApi.setAccessToken(clientCredentials.getAccessToken());
		 * 
		 * System.out.println("Expires in: " + clientCredentials.getExpiresIn()); }
		 * catch (InterruptedException | ExecutionException e) {
		 * System.out.println("Error: " + e.getCause().getMessage()); } }
		 */

	}
}
