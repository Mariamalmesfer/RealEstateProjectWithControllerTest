package com.example.realestateprojectwithsecurity;


import com.example.realestateprojectwithsecurity.Model.*;
import com.example.realestateprojectwithsecurity.Repository.PropertyRepository;
import com.example.realestateprojectwithsecurity.Repository.RatingsRepository;
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
public class RatingsRepositoryTest {


    @Autowired
    RatingsRepository ratingsRepository;

    Ratings ratings1;

    Ratings ratings2;


    Property property1;




    @BeforeEach
    void setUp() {
//        property1=new Property(1,"Property1","Property in riyadh","18.0m",2000.00,"Riyadh",3,3,null,null,null);
        ratings1=new Ratings(null,"good",8,property1,null);
        ratings1=new Ratings(null,"not bad",5,null,null);
//        user1=new User(null,"Mariam","Mm@123123","Agent",null,null);
//        agent1 = new Agent(null,user1,null,null);
//        user2=new User(null,"Reem","rr@123123","Agent",null,null);
////        customer2 = new Customer(null,user2,null,null);
////        deal1 = new Deal(null,"deal1",30000.0,false,null,agent1,customer2);

//        property2=new Property(2,"Property1","Property in riyadh","18.0m",2000.00,"Riyadh",3,3,null,null,null);
    }


    @Test
    public void findRatingsByIdtest(){
        ratingsRepository.save(ratings1);
        ratings2=ratingsRepository.findRatingsById(ratings1.getId());
        Assertions.assertThat(ratings2).isEqualTo(ratings1);

    }









}
