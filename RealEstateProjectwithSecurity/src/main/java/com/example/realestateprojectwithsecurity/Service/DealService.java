package com.example.realestateprojectwithsecurity.Service;


import com.example.realestateprojectwithsecurity.ApiResponse.ApiException;
import com.example.realestateprojectwithsecurity.DTO.CustomerDTO;
import com.example.realestateprojectwithsecurity.DTO.DealDTO;
import com.example.realestateprojectwithsecurity.Model.*;
import com.example.realestateprojectwithsecurity.Repository.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DealService {

    private final DealRepository dealRepository ;
    private final PropertyRepository propertyRepository;
    private final AgentRepository agentRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;



    public List<Deal> getAllDeals(){

        return dealRepository.findAll();
    }


    public List<Deal> getAgentDeals(Integer agent_id) {
        Agent agent =agentRepository.findAgentById(agent_id);
        return  dealRepository.findDealByAgent(agent);
    }


    public List<Deal> getCoustomesrtDeals(Integer c_id) {
        Customer customer =customerRepository.findCustomerById(c_id);
        return  dealRepository.findDealByCustomer(customer);
    }


    public void addDeal(Integer agent_id,DealDTO dealDTO ,String Coustomerusername) {
        Agent agent =agentRepository.findAgentById(agent_id);
        User user= userRepository.findUserByUsername(Coustomerusername);
        Customer customer=customerRepository.findCustomerByUser(user);
        if (customer==null){throw new ApiException("customer is null");}
        Property property = propertyRepository.findPropertiesById(dealDTO.getProperty_id());
        if (property == null) {
            throw new ApiException("id is null");
        }
        Deal deal = new Deal(null,dealDTO.getDescription(),dealDTO.getPrice(),false,property,agent,customer);
        dealRepository.save(deal);
    }




    public void updateDeal(Integer user_id,Integer id,DealDTO dealDTO) {
        Property property = propertyRepository.findPropertiesById(dealDTO.getProperty_id());
        Deal OldDeal= dealRepository.findDealById(id);
        if (OldDeal==null){
            throw new ApiException("OldDeal not found");
        }
        if (property == null) {
            throw new ApiException("property is null");
        }

        if (OldDeal.getAgent().getId() != user_id){
            throw new ApiException("user is not allowed");
        }

        OldDeal.setDescription(dealDTO.getDescription());
        OldDeal.setPrice(dealDTO.getPrice());
        OldDeal.setProperty(property);
        dealRepository.save(OldDeal);

    }

    public void deleteDeal(Integer user_id, Integer id) {
        Deal OldDeal= dealRepository.findDealById(id);
        Property property = propertyRepository.findPropertiesById(OldDeal.getProperty().getId());
        if (OldDeal==null){
            throw new ApiException("OldDeal not found");
        }
        if (property == null) {
            throw new ApiException("property is null");
        }
        if (OldDeal.getAgent().getId() != user_id){
            throw new ApiException("user is not allowed");
        }
        property.setDeal(null);
        propertyRepository.save(property);
        dealRepository.deleteById(id);
    }



//    public List<Deal> dealList (Integer id){
//        List<Deal> d=dealRepository.findDealByUserId(id);
//        if (d==null){
//            throw new ApiException("no deals");
//        }
//        return d;
//    }








}
