package com.example.realestateprojectwithsecurity.Service;

import com.example.realestateprojectwithsecurity.ApiResponse.ApiException;
import com.example.realestateprojectwithsecurity.DTO.AgentDTO;
import com.example.realestateprojectwithsecurity.DTO.CustomerDTO;
import com.example.realestateprojectwithsecurity.Model.Agent;
import com.example.realestateprojectwithsecurity.Model.Customer;
import com.example.realestateprojectwithsecurity.Model.User;
import com.example.realestateprojectwithsecurity.Repository.AgentRepository;
import com.example.realestateprojectwithsecurity.Repository.CustomerRepository;
import com.example.realestateprojectwithsecurity.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgentService {

    private final AgentRepository agentRepository;
    private final UserRepository userRepository;

    public void regester(AgentDTO agentDTO)
    {

        String hash = new BCryptPasswordEncoder().encode(agentDTO.getPassword());

        User user1 = new User(null,agentDTO.getUsername(), hash, "Agent",null,null);
        Agent agent =new Agent(null,user1,null,null);
        user1.setAgent(agent);

        agentRepository.save(agent);
        userRepository.save(user1);
    }
}
