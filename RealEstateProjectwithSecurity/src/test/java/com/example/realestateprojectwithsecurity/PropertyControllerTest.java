package com.example.realestateprojectwithsecurity;



import com.example.realestateprojectwithsecurity.Controller.PropertyController;
import com.example.realestateprojectwithsecurity.Model.Agent;
import com.example.realestateprojectwithsecurity.Model.Property;
import com.example.realestateprojectwithsecurity.Model.User;

import com.example.realestateprojectwithsecurity.Service.PropertyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = PropertyController.class ,excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class PropertyControllerTest {


    @MockBean
    PropertyService propertyService;

    @Autowired
    MockMvc mockMvc;

    Property property1,property2,property3;
    Agent agent;

    User user1;


    List<Property> properties,propertyList;


    @BeforeEach
    void setUp() {
        user1=new User(1,"Mariam","Mm@123123","Agent",null,null);
        agent = new Agent(null,user1,null,null);

        property1=new Property(1,"Property1","Rent","18.0m",2000.00,"Riyadh",3,3,agent,null,null);
        property2=new Property(null,"Property1","Rent","18.0m",2000.00,"Riyadh",3,3,agent,null,null);
        property3=new Property(1,"Property1","Rent","18.0m",2000.00,"Riyadh",3,3,agent,null,null);


        properties= Arrays.asList(property1);

        propertyList=Arrays.asList(property2);
    }



    @Test
    public void GetAllProperty() throws Exception {
        Mockito.when(propertyService.getAllProperty()).thenReturn(properties);
        mockMvc.perform(get("/api/v1/property/get"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Property1"));
    }



    @Test
    public void GetAllbyliocation() throws Exception {
        Mockito.when(propertyService.getbylocation(agent.getId(),property2.getLocation())).thenReturn(properties);
        mockMvc.perform(get("/api/v1/property/getbyloc/{location}",property2.getLocation()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Property1"));
    }


    @Test
    public void testAddProperty() throws  Exception {
        mockMvc.perform(post("/api/v1/property/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( new ObjectMapper().writeValueAsString(property1)))
                .andExpect(status().isOk());

    }


    @Test
    public void testDeleteProperty() throws Exception{
        mockMvc.perform(delete("/api/v1/property/delete/{id}",property1.getId()))
                .andExpect(status().isOk());

    }


    @Test
    public void testUpdateProperty()throws Exception{
        mockMvc.perform(put("/api/v1/property/update/{id}",property1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content( new ObjectMapper().writeValueAsString(property1)))
                .andExpect(status().isOk());

    }









}
