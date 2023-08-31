package com.example.realestateprojectwithsecurity.Repository;



import com.example.realestateprojectwithsecurity.Model.Agent;
import com.example.realestateprojectwithsecurity.Model.Customer;
import com.example.realestateprojectwithsecurity.Model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealRepository extends JpaRepository<Deal,Integer> {

    Deal findDealById (Integer id);

    List <Deal> findDealByAgent (Agent agent);

    List <Deal>  findDealByCustomer(Customer customer);


}
