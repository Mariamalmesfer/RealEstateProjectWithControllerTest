package com.example.realestateprojectwithsecurity;

import com.example.realestateprojectwithsecurity.Model.Agent;
import com.example.realestateprojectwithsecurity.Model.Customer;
import com.example.realestateprojectwithsecurity.Model.User;
import com.example.realestateprojectwithsecurity.Repository.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    User user1;

    User user2;
    Customer customer1;

    Customer customer2;


    @BeforeEach
    void setUp() {
        user1=new User(null,"Mariam","Mm@123123","Customer",null,null);
        user2=new User(null,"Reem","rr@123123","Customer",null,null);
        customer1 = new Customer(null,user1,null,null);
        customer2 = new Customer(null,user2,null,null);
    }


    @Test
    public void  findcustomerbyidtest(){
        customerRepository.save(customer1);
        customer2=customerRepository.findCustomerById(customer1.getId());
        Assertions.assertThat(customer2).isEqualTo(customer1);

    }


    @Test
    public void  findCustomerByUsertst(){
        customerRepository.save(customer1);
        customer2=customerRepository.findCustomerByUser(customer1.getUser());
        Assertions.assertThat(customer2).isEqualTo(customer1);

    }

}
