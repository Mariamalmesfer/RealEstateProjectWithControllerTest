package com.example.realestateprojectwithsecurity.Repository;


import com.example.realestateprojectwithsecurity.Model.Agent;
import com.example.realestateprojectwithsecurity.Model.Customer;
import com.example.realestateprojectwithsecurity.Model.Property;
import com.example.realestateprojectwithsecurity.Model.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PropertyRepository extends JpaRepository<Property,Integer> {

    Property findPropertiesById (Integer id);

    List<Property> findPropertiesByLocation (String location );

    List<Property> findPropertiesBySize (String size);

   // List<Property> findPropertiesByPrice(Integer price);

    List<Property> findAllByAgent(Agent agent);




}
