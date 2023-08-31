package com.example.realestateprojectwithsecurity;


import com.example.realestateprojectwithsecurity.Model.User;
import com.example.realestateprojectwithsecurity.Repository.UserRepository;
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
public class UserRepositorytest {



    @Autowired
    UserRepository userRepository;


    User user1;


    User user2;

    @BeforeEach
    void setUp() {

        user1=new User(null,"Mariam","Mm@123123","Agent",null,null);
        user2=new User(null,"Reem","rr@123123","Customer",null,null);

    }


    @Test
    public void findUserByUsernametest(){
        userRepository.save(user1);
        user2=userRepository.findUserByUsername(user1.getUsername());
        Assertions.assertThat(user2).isEqualTo(user1);
    }
}
