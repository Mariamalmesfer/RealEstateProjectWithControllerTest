package com.example.realestateprojectwithsecurity.Repository;


import com.example.realestateprojectwithsecurity.Model.Customer;
import com.example.realestateprojectwithsecurity.Model.Property;
import com.example.realestateprojectwithsecurity.Model.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingsRepository extends JpaRepository<Ratings,Integer> {

    Ratings findRatingsById(Integer id);

    List<Ratings> findRatingsByProperty (Property property);
}
