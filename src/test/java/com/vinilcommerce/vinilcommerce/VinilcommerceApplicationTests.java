package com.vinilcommerce.vinilcommerce;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.vinilcommerce.model.Genre;
import com.vinilcommerce.model.Product;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class VinilcommerceApplicationTests {

	private static final String ENDPOINT_ALBUM = "/api/album";
	private static final String ENDPOINT_SALE = "/api/sale";
	
	private Response responseAlbum;
	private Response responseSale;

	@Before
    public void init() throws IOException {

        RestAssured.port = 8080;
        this.responseAlbum = RestAssured.given().get(ENDPOINT_ALBUM);
        this.responseSale = RestAssured.given().get(ENDPOINT_SALE);

    }

    @Test
    public void checkHeaderResponse() {
        this.responseAlbum.then().statusCode(200).and().contentType(ContentType.JSON);
        this.responseSale.then().statusCode(200).and().contentType(ContentType.JSON);
    }
	
    // consultar o catalogo de discos por genero, paginacao, todos de uma vez
    @Test
    public void searchAlbumByGenre() {
    	
    	List<Genre> genres = Arrays.asList(Genre.values());
		genres.stream().forEach(genre -> {
			String url = ENDPOINT_ALBUM + "?genre=" + genre.name();
			RestAssured.given().get(url).then().statusCode(200).and().contentType(ContentType.JSON);
			List<Product> products = RestAssured.given().get(url).andReturn().getBody().jsonPath().getList("content", Product.class);
			assertNotNull(products);
		});
    	
    }
    
    @Test
    public void searchPaginateAlbumByGenre() {
    	
    	List<Genre> genres = Arrays.asList(Genre.values());
		genres.stream().forEach(genre -> {
			String url = ENDPOINT_ALBUM + "?genre=" + genre.name() + "&size=10&page=1";
			RestAssured.given().get(url).then().statusCode(200).and().contentType(ContentType.JSON);
			int size = RestAssured.given().get(url).andReturn().getBody().jsonPath().getList("content", Product.class).size();
			assertTrue(size == 10);
		});
    	
    }
    
    // consultar o disco por ID
    @Test
    public void searchAlbumById() {
    	String id = "1";
		String url = ENDPOINT_ALBUM + "/" + id;
		RestAssured.given().get(url).then().statusCode(200).and().contentType(ContentType.JSON);
    }
    
    // consultar todas as vendas paginada, por range de datas e ordenados
    @Test
    public void searchPaginateSalesByDates() {
    	String url = ENDPOINT_SALE + "?start=01/06/2019&end=09/06/2019&size=1&page=0";
    	RestAssured.given().get(url).then().statusCode(200).and().contentType(ContentType.JSON);
    	List<Product> sales = RestAssured.given().get(url).andReturn().getBody().jsonPath().getList("content", Product.class);
    	System.out.println(sales.size());
    	//assertTrue(!sales.isEmpty());
    }
    
    // consultar a venda por ID
    @Test
    public void searchSaleById() {
    	String id = "1";
		String url = ENDPOINT_SALE + "/" + id;
		RestAssured.given().get(url).then().statusCode(200).and().contentType(ContentType.JSON);
    }
    
    // realizar uma venda
    @Test
    public void registerSale() {
    	//Sale sale = new Sale();
    }
    
}
