package com.example.realestateprojectwithsecurity;

import com.example.realestateprojectwithsecurity.Model.Agent;
import com.example.realestateprojectwithsecurity.Model.Customer;
import com.example.realestateprojectwithsecurity.Model.Property;
import com.example.realestateprojectwithsecurity.Model.User;
import com.example.realestateprojectwithsecurity.Repository.AgentRepository;
import com.example.realestateprojectwithsecurity.Repository.CustomerRepository;
import com.example.realestateprojectwithsecurity.Repository.PropertyRepository;
import com.example.realestateprojectwithsecurity.Service.PropertyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {

    @InjectMocks
    PropertyService propertyService;


    @Mock
    PropertyRepository propertyRepository;

    @Mock
    AgentRepository agentRepository;



    User user1;

    User user2;
    Agent agent;

    Agent agent2;

    Property property1;


    Property property2;


    List<Property> properties;


    @BeforeEach
    void setUp() {
        user1=new User(null,"Mariam","Mm@123123","Agent",null,null);
        user2=new User(null,"Reem","rr@123123","Agent",null,null);
        agent = new Agent(null,user1,null,null);
        agent2 = new Agent(null,user2,null,null);


                property1=new Property(1,"Property1","Property in riyadh","18.0m",2000.00,"Riyadh",3,3,agent,null,null);
                property2=new Property(null,"Property1","Property in riyadh","18.0m",2000.00,"Riyadh",3,3,agent2,null,null);


                properties = new ArrayList<>();
                properties.add(property1);
                properties.add(property2);
    }


    @Test
    public void getallPropertytest(){
        when(propertyRepository.findAll()).thenReturn(properties);


        List<Property> PropertyList =propertyService.getAllProperty();
        Assertions.assertEquals(PropertyList,properties);


        Mockito.verify(propertyRepository,times(1)).findAll();
    }



    @Test
    public  void  GetmyProperty(){
        when(agentRepository.findAgentById(agent.getId())).thenReturn(agent);
        when(propertyRepository.findAllByAgent(agent)).thenReturn(properties);

        List<Property> PropertyList =propertyService.getMyProperty(agent.getId());
        Assertions.assertEquals(PropertyList,properties);

        verify(propertyRepository,times(1)).findAllByAgent(agent);
        verify(agentRepository,times(1)).findAgentById(agent.getId());

    }

    @Test
    public void AddPropertytest(){

        when(agentRepository.findAgentById(agent.getId())).thenReturn(agent);


        //save any todos

        propertyService.addProperty(agent.getId(),property2);




        verify(propertyRepository,times(1)).save(property2);
        verify(agentRepository,times(1)).findAgentById(agent.getId());
    }


    @Test
    public void update(){

        when(propertyRepository.findPropertiesById(property1.getId())).thenReturn(property1);
        when(agentRepository.findAgentById(agent.getId())).thenReturn(agent);

        propertyService.updateProperty(agent.getId(),property1.getId(),property2);


        verify(propertyRepository,times(1)).findPropertiesById(property1.getId());
        verify(agentRepository,times(1)).findAgentById(agent.getId());
        verify(propertyRepository,times(1)).save(property1);


    }



    @Test
    public void Delete(){
        when(propertyRepository.findPropertiesById(property1.getId())).thenReturn(property1);

        propertyService.deleteProperty(user1.getId(),property1.getId());

        verify(propertyRepository,times(1)).findPropertiesById(property1.getId());
        verify(propertyRepository,times(1)).delete(property1);

    }




}
