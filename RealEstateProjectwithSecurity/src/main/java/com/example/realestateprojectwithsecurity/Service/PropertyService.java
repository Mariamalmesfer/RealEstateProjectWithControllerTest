package com.example.realestateprojectwithsecurity.Service;



import com.example.realestateprojectwithsecurity.ApiResponse.ApiException;
import com.example.realestateprojectwithsecurity.Model.Agent;
import com.example.realestateprojectwithsecurity.Model.Customer;
import com.example.realestateprojectwithsecurity.Model.Property;
import com.example.realestateprojectwithsecurity.Repository.AgentRepository;
import com.example.realestateprojectwithsecurity.Repository.CustomerRepository;
import com.example.realestateprojectwithsecurity.Repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {


    private final PropertyRepository propertyRepository;
    private final AgentRepository agentRepository;
    private final CustomerRepository customerRepository;


    public List<Property> getAllProperty(){

        return propertyRepository.findAll();
    }


    public List<Property> getMyProperty(Integer agent_id) {
        Agent agent =agentRepository.findAgentById(agent_id );
        return   propertyRepository.findAllByAgent(agent);
    }



    public void addProperty(Integer agent_id ,Property property){
        Agent agent =agentRepository.findAgentById(agent_id );
        property.setAgent(agent);
        propertyRepository.save(property);
    }

    public void updateProperty(Integer user_id,Integer id,Property property) {
         Agent agent = agentRepository.findAgentById(user_id);
        Property OldProperty= propertyRepository.findPropertiesById(id);
        if (OldProperty==null){
            throw new ApiException("id not found");
        }
        if (OldProperty.getAgent() != agent){
            throw new ApiException("user is not allowed");
        }
        OldProperty.setTitle(property.getTitle());
        OldProperty.setDescription(property.getDescription());
        OldProperty.setSize(property.getSize());
        OldProperty.setPrice(property.getPrice());
        OldProperty.setLocation(property.getLocation());
        OldProperty.setBedrooms(property.getBedrooms());
        OldProperty.setBathrooms(property.getBathrooms());

        propertyRepository.save(OldProperty);

    }

    public void deleteProperty(Integer user_id,Integer id) {
        Property OldProperty= propertyRepository.findPropertiesById(id);

        if (OldProperty==null){
            throw new ApiException("id not found");
        }

        if (OldProperty.getAgent().getId() != user_id){
            throw new ApiException("user is not allowed");
        }
        propertyRepository.delete(OldProperty);
    }






    // get all Property by location
    public List<Property> getbylocation(Integer user_id,String location){
        Customer customer = customerRepository.findCustomerById(user_id);
        if (customer==null){
            throw new ApiException("id not found");
        }
        return propertyRepository.findPropertiesByLocation(location);
    }


    // get all Property  by size
    public List<Property> getbySize(Integer user_id,String size){
        Customer customer = customerRepository.findCustomerById(user_id);
        if (customer==null){
            throw new ApiException("id not found");
        }
        List<Property> property= propertyRepository.findPropertiesBySize(size);
        if (property.isEmpty()){
            throw new ApiException("Property in this size not found");
        }
        return property;
    }



    // sort the price from low to hifh
    public List<Property> sortbyprice(Integer user_id){
        Customer customer = customerRepository.findCustomerById(user_id);
        if (customer==null){
            throw new ApiException("user not allowed");
        }
        List<Property> property= getAllProperty();
        if (property.isEmpty()){
            throw new ApiException("Property in this size not found");
        }
         property.sort(Comparator.comparingDouble(Property::getPrice));
        return property;
    }


















}
