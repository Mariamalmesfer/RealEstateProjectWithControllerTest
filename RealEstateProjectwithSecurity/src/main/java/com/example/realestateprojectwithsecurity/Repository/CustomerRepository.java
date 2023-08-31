package com.example.realestateprojectwithsecurity.Repository;

import com.example.realestateprojectwithsecurity.Model.Customer;
import com.example.realestateprojectwithsecurity.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findCustomerById(Integer id);

    Customer findCustomerByUser (User user);


}
