package com.example.primeservice2.repository;

import com.example.primeservice2.model.Customer;
import org.springframework.data.repository.CrudRepository;


public interface AuthenticationDBRepository extends CrudRepository<Customer, String> {
    Customer findByUsername(String username);
}
