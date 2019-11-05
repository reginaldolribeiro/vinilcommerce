package com.vinilcommerce.vinilcommerce;

import com.vinilcommerce.model.Cashback;
import com.vinilcommerce.model.Genre;
import com.vinilcommerce.repository.CashbackRepository;
import org.assertj.core.api.Assertions;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class CashbackRepositoryTest {

    @Autowired
    private CashbackRepository cashbackRepository;

//    @Before
//    public void init(){
//        // Create the Flyway instance and point it to the database
//        Flyway flyway = Flyway.configure().dataSource("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1", "sa", "sa").load();
//
//        // Start the migration
//        flyway.migrate();
//    }

    @Test
    public void itShouldReturnCashbacks(){
        List<Cashback> cashbacks = this.cashbackRepository.findAll();

        Cashback cashback = cashbacks.stream().filter(c -> c.getGenre().equals(Genre.ROCK)).findAny().orElse(null);
        assertThat(cashback).hasFieldOrPropertyWithValue("genre", Genre.ROCK);

        assertThat(cashbacks.size()).isEqualTo(28);
        assertThat(cashbacks.get(0)).isInstanceOf(Cashback.class);
    }

    @Test
    public void itShouldReturnCashbacksFindByGenreAndDayOfWeek(){
        Cashback cashback = this.cashbackRepository.findByGenreAndDayOfWeek(Genre.ROCK, DayOfWeek.MONDAY);

        assertThat(cashback.getGenre()).isEqualTo(Genre.ROCK);
        assertThat(cashback.getValue()).isEqualByComparingTo(BigDecimal.TEN);
    }

}
