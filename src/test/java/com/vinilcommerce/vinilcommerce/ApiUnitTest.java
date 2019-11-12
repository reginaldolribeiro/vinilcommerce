package com.vinilcommerce.vinilcommerce;

import com.vinilcommerce.model.*;
import com.vinilcommerce.repository.CustomerRepository;
import com.vinilcommerce.repository.ProductRepository;
import com.vinilcommerce.repository.SaleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ApiUnitTest {

    private static final String ENDPOINT_ALBUM = "/api/album";
    private static final String ENDPOINT_SALE = "/api/sale";

    @LocalServerPort
    int port;

    @MockBean
    public ProductRepository productRepository;
    @MockBean
    public SaleRepository saleRepository;
    @MockBean
    public CustomerRepository customerRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    private Product youthanasia;
    private Product countdownToExtinction;

    @Before
    public void init(){
        youthanasia = new Product("Youthanasia", "Megadeth", Genre.ROCK, new BigDecimal(50));
        youthanasia.setId(1L);

        countdownToExtinction = new Product("Countdown to Extinction", "Megadeth", Genre.ROCK, new BigDecimal(50));
        countdownToExtinction.setId(1L);
    }

    @Test
    public void itShouldReturn200WhenFindAlbumByGenre() {

        String url = ENDPOINT_ALBUM + "?genre=ROCK&size=10&page=0";

        List<Product> products = new ArrayList<>();
        products.add(youthanasia);
        products.add((countdownToExtinction));

        //Page<Product> albums = Mockito.mock(Page.class);
        Page<Product> albums = new PageImpl(products);
        Pageable pageable = PageRequest.of(0, 10);

        BDDMockito.when(this.productRepository.findByGenreOrderByName(Genre.ROCK, pageable)).thenReturn(albums);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        Assert.assertEquals(response.getStatusCodeValue(), 200);
    }

    @Test
    public void itShouldReturnAlbumById() {
        String id = "1";
        String url = ENDPOINT_ALBUM + "/" + id;

        BDDMockito.when(this.productRepository.findById(youthanasia.getId())).thenReturn(Optional.of(youthanasia));

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

        Assert.assertEquals(response.getStatusCodeValue(), 200);
        Assert.assertEquals(youthanasia.getName(), response.getBody().getName());
        Assert.assertEquals(youthanasia.getArtistName(), response.getBody().getArtistName());
    }

    @Test
    public void itShouldReturnSaleById(){

        Customer customer = this.customerRepository.findById(3L).orElse(null);
        Product product1 = this.productRepository.findById(25L).orElse(null);
        Product product2 = this.productRepository.findById(56L).orElse(null);

        Sale sale = new Sale();
        sale.setId(1L);
        sale.setCustomer(customer);

        ItemSale itemSale1 = new ItemSale();
        itemSale1.setProduct(product1);

        ItemSale itemSale2 = new ItemSale();
        itemSale2.setProduct(product2);

        List<ItemSale> itens = new ArrayList<>();
        itens.add(itemSale1);
        itens.add(itemSale2);

        sale.setItens(itens);

        String url = ENDPOINT_SALE + "/1";

        BDDMockito.when(this.saleRepository.findById(sale.getId())).thenReturn(Optional.of(sale));
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        Assert.assertEquals(response.getStatusCodeValue(), 200);
    }

//    @Test
//    public void itShouldReturnSaleById(){
//        Customer customer = this.customerRepository.findById(3L).orElse(null);
//        Product product1 = this.productRepository.findById(25L).orElse(null);
//        Product product2 = this.productRepository.findById(56L).orElse(null);
//
//        Sale sale = new Sale();
//        sale.setCustomer(customer);
//
//        ItemSale itemSale1 = new ItemSale();
//        itemSale1.setProduct(product1);
//
//        ItemSale itemSale2 = new ItemSale();
//        itemSale2.setProduct(product2);
//
//        List<ItemSale> itens = new ArrayList<>();
//        itens.add(itemSale1);
//        itens.add(itemSale2);
//
//        sale.setItens(itens);
//
//        System.out.print(sale);
//
//        BDDMockito.when().thenReturn();
//
//    }


}
