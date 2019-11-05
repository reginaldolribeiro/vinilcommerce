package com.vinilcommerce.vinilcommerce;

import com.vinilcommerce.model.Genre;
import com.vinilcommerce.model.Product;
import com.vinilcommerce.repository.ProductRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ApiTest {

    private static final String ENDPOINT_ALBUM = "/api/album";
    private static final String ENDPOINT_SALE = "/api/sale";

    @LocalServerPort
    int port;

    @MockBean
    public ProductRepository productRepository;

    @Autowired
    private TestRestTemplate restTemplate;

//    @Test
//    public void itShouldReturn200WhenFindAlbumByGenre() {
//        String genre = "ROCK";
//        String url = ENDPOINT_ALBUM + "/" + genre;
//
//        Product youthanasiaAlbum = new Product();
//        youthanasiaAlbum.setId(1L);
//        youthanasiaAlbum.setArtistName("Megadeth");
//        youthanasiaAlbum.setGenre(Genre.ROCK);
//        youthanasiaAlbum.setName("Youthanasia");
//        youthanasiaAlbum.setPrice(new BigDecimal(50));
//
//        Product countdownToExtinction = new Product();
//        countdownToExtinction.setId(1L);
//        countdownToExtinction.setArtistName("Megadeth");
//        countdownToExtinction.setGenre(Genre.ROCK);
//        countdownToExtinction.setName("Countdown to Extinction");
//        countdownToExtinction.setPrice(new BigDecimal(50));
//
//        Page<Product> page = Mockito.mock(Page.class);
//        System.out.print("Paget content " + page.getContent().);
//
//
//        List<Product> albums = Arrays.asList(youthanasiaAlbum, countdownToExtinction);
//        //"http://localhost:8080/products{?page,size,sort}",
//
////        Pageable pageable = null;
////        BDDMockito.when(this.productRepository.findByGenreOrderByName(Genre.ROCK, null)).thenReturn(albums);
////
////        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
////        Assert.assertEquals(response.getStatusCodeValue(), 200);
//
//    }

    @Test
    public void itShouldReturn200WhenFindAlbumById() {
        String id = "1";
        String url = ENDPOINT_ALBUM + "/" + id;

        Product product = new Product();
        product.setId(1L);
        product.setArtistName("Megadeth");
        product.setGenre(Genre.ROCK);
        product.setName("Youthanasia");
        product.setPrice(new BigDecimal(50));

        BDDMockito.when(this.productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        Assert.assertEquals(response.getStatusCodeValue(), 200);

    }

    @Test
    public void itShouldReturnAlbumById() {
        String id = "1";
        String url = ENDPOINT_ALBUM + "/" + id;

        Product product = new Product();
        product.setId(1L);
        product.setArtistName("Megadeth");
        product.setGenre(Genre.ROCK);
        product.setName("Youthanasia");
        product.setPrice(new BigDecimal(50));

        BDDMockito.when(this.productRepository.findById(product.getId())).thenReturn(Optional.of(product));

//        Response response = RestAssured
//                .given()
//                .get(url)
//                .then()
//                .statusCode(200)
//                .and()
//                .contentType(ContentType.JSON)
//                .extract()
//                .response();
//        String name = response.jsonPath().getString("name");
//        Assert.assertEquals(name, product.getName());

        ResponseEntity<Product> response = restTemplate.getForEntity(url, Product.class);
        System.out.print("*****Response " + response.getBody());

        Assert.assertEquals(product.getName(), response.getBody().getName());
        Assert.assertEquals(product.getArtistName(), response.getBody().getArtistName());

    }


}
