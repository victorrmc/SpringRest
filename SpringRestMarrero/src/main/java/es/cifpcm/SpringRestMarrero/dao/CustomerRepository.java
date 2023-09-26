package es.cifpcm.SpringRestMarrero.dao;

import es.cifpcm.SpringRestMarrero.modelo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByCustomerid(Integer customerid);

}