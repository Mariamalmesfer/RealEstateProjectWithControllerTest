package com.example.realestateprojectwithsecurity.Service;


import com.example.realestateprojectwithsecurity.ApiResponse.ApiException;
import com.example.realestateprojectwithsecurity.Model.*;
import com.example.realestateprojectwithsecurity.Repository.CustomerRepository;
import com.example.realestateprojectwithsecurity.Repository.PropertyRepository;
import com.example.realestateprojectwithsecurity.Repository.RatingsRepository;
import com.example.realestateprojectwithsecurity.Repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingsService {


    private final PropertyRepository propertyRepository;
    private  final RatingsRepository ratingsRepository;
    private  final CustomerRepository customerRepository;

    public List<Ratings> getrateing(){

        return ratingsRepository.findAll();
    }

   public void addRarting (Integer user_id, Ratings ratings, Integer property_ID ) {
    Property properties = propertyRepository.findPropertiesById(property_ID);
    if (properties == null) {
        throw new ApiException("id not found");}
       Customer customer = customerRepository.findCustomerById(user_id);
        ratings.setCustomer(customer);
        ratings.setProperty(properties);
        ratingsRepository.save(ratings);

       ////    Customer customer = customerRepository.findCustomerById(user_id);
////    if (user == null) {
////        throw new ApiException("id not found");}
//
//    if (properties.getDescription().equals("Rent") ) {
//         List<Ratings> ratings1 = getrateing();
//        if (ratings1 == null){ ratingsRepository.save(ratings);}
////            for (Ratings r : ratings1) {
////                if (r.getUser_id().equals(ratings.getUser_id())&& r.getProperty_id().equals(ratings.getProperty_id())) {
////                    throw new ApiException("already rate the property ");
////                }
//
//            }
}

    public void deleteRating(Integer user_id, Integer id) {
        Ratings ratingsl= ratingsRepository.findRatingsById(id);
        Property property = propertyRepository.findPropertiesById(ratingsl.getProperty().getId());
        if (ratingsl==null){
            throw new ApiException("rating not found");
        }
        if (property == null) {
            throw new ApiException("property is null");
        }
        if (ratingsl.getCustomer().getId() != user_id){
            throw new ApiException("user is not allowed");
        }
        property.setRatings(null);
        propertyRepository.save(property);
        ratingsRepository.deleteById(id);
    }



    public List<Ratings> getraitinbyid (Integer user_id,Integer pid) {
        Customer customer = customerRepository.findCustomerById(user_id);
        if (customer==null){
            throw new ApiException("user not allowed");
        }
        Property property = propertyRepository.findPropertiesById(pid);
        return ratingsRepository.findRatingsByProperty(property);

    }

}
