package com.example.realestateprojectwithsecurity.Service;

import com.example.realestateprojectwithsecurity.ApiResponse.ApiException;
import com.example.realestateprojectwithsecurity.DTO.CustomerDTO;
import com.example.realestateprojectwithsecurity.Model.Customer;
import com.example.realestateprojectwithsecurity.Model.User;
import com.example.realestateprojectwithsecurity.Repository.CustomerRepository;
import com.example.realestateprojectwithsecurity.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public void regester(CustomerDTO customerDTO)
    {

        String hash = new BCryptPasswordEncoder().encode(customerDTO.getPassword());


        User user1 = new User(null,customerDTO.getUsername(), hash, "Customer",null,null);
        Customer customer =new Customer(null,user1,null,null);
        user1.setCustomer(customer);

        customerRepository.save(customer);
        userRepository.save(user1);
    }





}
