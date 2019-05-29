package com.vinilcommerce.vinilcommerce;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.requests.data.albums.GetSeveralAlbumsRequest;

@RestController
public class VinilController {
	
	private static final String[] ids = new String[]{"0LcJLqbBmaGUft1e9Mm8HV"};

	@GetMapping("/test")
	public String teste() throws SpotifyWebApiException, IOException {
		
		// For all requests an access token is needed
		SpotifyApi spotifyApi = new SpotifyApi.Builder()
		        .setAccessToken("BQBhM5tSXzb8gwQqiKx-yaiwklAPQ8JYjlGl7EYsi8BbUL_vd0PyHkIet804u2_DFA-ZkQwgRUklas7X6CqoKjl2ssApFfUbLE3AYXPR43dsj8JwZTA_lnUqx34Wyyz0TxCVrjTlvHogjxAoa5IgCGMUAmekRWaZSdTOK2l_bcLyug")
		        .build();
		
		GetSeveralAlbumsRequest severalAlbumsRequest = spotifyApi.getSeveralAlbums(ids).build();
		Album[] albums = severalAlbumsRequest.execute();
		
		System.out.println("Length: " + albums.length);
		
		try {
		      final Future<Album[]> albumsFuture = severalAlbumsRequest.executeAsync();

		      // ...

		      final Album[] albums1 = albumsFuture.get();

		      System.out.println("Length: " + albums1.length);
		    } catch (InterruptedException | ExecutionException e) {
		      System.out.println("Error: " + e.getCause().getMessage());
		    }
		return "Test!";
	}
	
}
