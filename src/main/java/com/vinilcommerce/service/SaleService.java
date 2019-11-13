package com.vinilcommerce.service;

import com.vinilcommerce.model.Customer;
import com.vinilcommerce.model.ItemSale;
import com.vinilcommerce.model.Product;
import com.vinilcommerce.model.Sale;
import com.vinilcommerce.repository.CustomerRepository;
import com.vinilcommerce.repository.ProductRepository;
import com.vinilcommerce.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CashbackService cashbackService;

    public Sale createSale(Sale sale){

        Long customerId = sale.getCustomer().getId();
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer == null) return null;

        sale.setCustomer(customer);

        for (ItemSale item : sale.getItens()){
            Long idProduct = item.getProduct().getId();
            Product product = productRepository.findById(idProduct).orElse(null);
            System.out.print("Product " + product);

            if (product == null) return null;

            item.setProduct(product);
            item.setSale(sale);
            item = cashbackService.calculate(item);
        }

        sale.calculateTotalCashback();
        sale = saleRepository.save(sale);

        return sale;

    }

}
