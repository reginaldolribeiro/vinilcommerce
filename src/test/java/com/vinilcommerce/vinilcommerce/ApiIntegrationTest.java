package com.vinilcommerce.vinilcommerce;

import com.vinilcommerce.model.*;
import com.vinilcommerce.repository.CustomerRepository;
import com.vinilcommerce.repository.ProductRepository;
import com.vinilcommerce.service.SaleService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
public class ApiIntegrationTest {

    private static final String ENDPOINT_ALBUM = "/api/album";
    private static final String ENDPOINT_SALE = "/api/sale";

    private Response responseAlbum;
    private Response responseSale;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SaleService saleService;

    @LocalServerPort
    int port;

    @Before
    public void init() throws IOException {

        RestAssured.port = port;
        this.responseAlbum = RestAssured.given().get(ENDPOINT_ALBUM);
        this.responseSale = RestAssured.given().get(ENDPOINT_SALE);
    }

    @Test
    public void checkHeaderResponse() {
        this.responseAlbum.then().statusCode(200).and().contentType(ContentType.JSON);
        //this.responseSale.then().statusCode(200).and().contentType(ContentType.JSON);
    }

    @Test
    public void searchAlbums() {

        RestAssured.given().get(ENDPOINT_ALBUM).then().statusCode(200).and().contentType(ContentType.JSON);
        List<Product> products = RestAssured.given().get(ENDPOINT_ALBUM).andReturn().getBody().jsonPath().getList("content",
                Product.class);
        assertNotNull(products);
        assertTrue(!products.isEmpty());

    }

    @Test
    public void searchAlbumByGenre() {

        List<Genre> genres = Arrays.asList(Genre.values());
        genres.stream().forEach(genre -> {
            String url = ENDPOINT_ALBUM + "?genre=" + genre.name();
            RestAssured.given().get(url).then().statusCode(200).and().contentType(ContentType.JSON);
            List<Product> products = RestAssured.given().get(url).andReturn().getBody().jsonPath().getList("content",
                    Product.class);
            assertNotNull(products);
        });

    }

    @Test
    public void searchPaginateAlbumByGenre() {

        List<Genre> genres = Arrays.asList(Genre.values());
        genres.stream().forEach(genre -> {
            String url = ENDPOINT_ALBUM + "?genre=" + genre.name() + "&size=10&page=1";
            RestAssured.given().get(url).then().statusCode(200).and().contentType(ContentType.JSON);
            int size = RestAssured.given().get(url).andReturn().getBody().jsonPath().getList("content", Product.class)
                    .size();
            assertTrue(size == 10);
        });

    }

    @Test
    public void searchAlbumById() {
        String id = "1";
        String url = ENDPOINT_ALBUM + "/" + id;
        RestAssured.given().get(url).then().statusCode(200).and().contentType(ContentType.JSON);
    }

    @Test
    public void searchAlbumThatDoesntExist() {
        String id = "99999999";
        String url = ENDPOINT_ALBUM + "/" + id;
        RestAssured.given().get(url).then().statusCode(404);
    }

    @Test
    public void createSale(){

        Sale sale = createInitialSale();

        RequestSpecification request = RestAssured.given();
        Response response = request
                .contentType("application/json")
                .accept("application/json")
                .body(sale)
                .when()
                .post(ENDPOINT_SALE)
                .then()
                .statusCode(201)
                .contentType("application/json")
                .extract()
                .response();

        assertTrue(response.statusCode() == 201);

        List<ItemSale> itemSales = response.jsonPath().getList("itens", ItemSale.class);
        assertEquals(itemSales.size(), 2);

        long id = response.jsonPath().getLong("id");
        String url = ENDPOINT_SALE + "/" + this.id;
        RestAssured.given().get(url).then().statusCode(200).and().contentType(ContentType.JSON);

    }

	@Test
	public void searchPaginateSalesByDates() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate now = LocalDate.now();
        String startDate = now.format(formatter);
        String endDate = now.plusDays(30).format(formatter);

        String url = ENDPOINT_SALE + "?start=" + startDate + "&end=" + endDate + "&size=1&page=0";
		RestAssured.given().get(url).then().statusCode(200).and().contentType(ContentType.JSON);
	}

    @Test
    public void searchPaginateSalesWithWrongDates() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate now = LocalDate.now();
        String startDate = now.format(formatter);
        String endDate = now.minusDays(30).format(formatter);

        String url = ENDPOINT_SALE + "?start=" + startDate + "&end=" + endDate + "&size=1&page=0";
        RestAssured.given().get(url).then().statusCode(404);
    }

    private Sale createInitialSale() {
        Customer customer = this.customerRepository.findById(3L).orElse(null);
        Product product1 = this.productRepository.findById(25L).orElse(null);
        Product product2 = this.productRepository.findById(56L).orElse(null);

        Sale sale = new Sale();
        sale.setCustomer(customer);

        ItemSale itemSale1 = new ItemSale();
        itemSale1.setProduct(product1);

        ItemSale itemSale2 = new ItemSale();
        itemSale2.setProduct(product2);

        List<ItemSale> itens = new ArrayList<>();
        itens.add(itemSale1);
        itens.add(itemSale2);

        sale.setItens(itens);
        return sale;
    }

}
