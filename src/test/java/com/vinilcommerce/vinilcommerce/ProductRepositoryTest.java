package com.vinilcommerce.vinilcommerce;

import com.vinilcommerce.model.Cashback;
import com.vinilcommerce.model.Genre;
import com.vinilcommerce.model.Product;
import com.vinilcommerce.repository.CashbackRepository;
import com.vinilcommerce.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

//    @Before
//    public void init(){
//        // Create the Flyway instance and point it to the database
//        Flyway flyway = Flyway.configure().locations("classpath:/db/migration/h2").dataSource("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1", "sa", "sa").load();
//
//        System.out.print("************** FLYWAY ******* Flyway locations: " + Flyway.configure().getLocations().toString());
//
//        // Start the migration
//        System.out.print("*** FLYWAY Rodando a migracao ...");
//        flyway.migrate();
//    }

    @Test
    public void itShouldReturnProductOfGenreRock(){
        Page<Product> product = this.productRepository.findByGenreOrderByName(Genre.ROCK, null);
        System.out.print("**PRODUCT " + product.getContent().size());

        assertThat(product.getContent().get(0).getGenre()).isEqualTo(Genre.ROCK);
    }

}
