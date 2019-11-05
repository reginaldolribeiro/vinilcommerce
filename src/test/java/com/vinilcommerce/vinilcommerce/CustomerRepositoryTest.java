package com.vinilcommerce.vinilcommerce;

import com.vinilcommerce.model.Customer;
import com.vinilcommerce.model.Sale;
import com.vinilcommerce.repository.CustomerRepository;
import com.vinilcommerce.repository.SaleRepository;
import org.assertj.core.api.Assertions;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
// Serve para indicar que os testes vao ser feitos no banco de dados real (de producao),
// caso nao seja incluido ele procura o banco de testes, neste caso o H2
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void itShouldPersistDate(){
        Customer customer = new Customer();
        customer.setName("Customer test");
        customer.setEmail("customer-test@gmail.com");
        customer.setBirthDate(LocalDate.of(1995,5, 20));

        customerRepository.save(customer);

        Assertions.assertThat(customer.getId()).isNotNull();
        Assertions.assertThat(customer.getName()).isEqualTo("Customer test");
    }

    @Test
    public void itShouldReturnCustomer(){
        List<Customer> customers = this.customerRepository.findAll();
        Assertions.assertThat(customers.size()).isNotZero();
        Assertions.assertThat(customers.size()).isEqualTo(3);
    }

}
