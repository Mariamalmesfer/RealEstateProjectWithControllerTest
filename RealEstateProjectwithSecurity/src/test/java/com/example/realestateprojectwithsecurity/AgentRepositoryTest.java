package com.example.realestateprojectwithsecurity;


import com.example.realestateprojectwithsecurity.Model.Agent;
import com.example.realestateprojectwithsecurity.Model.User;
import com.example.realestateprojectwithsecurity.Repository.AgentRepository;
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
public class AgentRepositoryTest {


    @Autowired
    AgentRepository agentRepository;




    User user1;

    User user2;
    Agent agent1;

    Agent agent2;

    @BeforeEach
    void setUp() {
        user1=new User(null,"Mariam","Mm@123123","Agent",null,null);
        user2=new User(null,"Reem","rr@123123","Agent",null,null);
        agent1 = new Agent(null,user1,null,null);
        agent2 = new Agent(null,user2,null,null);
    }


    @Test
    public void  findagentbyidtest(){
        agentRepository.save(agent1);
        agent2=agentRepository.findAgentById(agent1.getId());
        Assertions.assertThat(agent2).isEqualTo(agent1);

    }
}
