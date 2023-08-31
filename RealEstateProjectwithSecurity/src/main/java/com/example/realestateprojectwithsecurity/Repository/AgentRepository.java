package com.example.realestateprojectwithsecurity.Repository;

import com.example.realestateprojectwithsecurity.Model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent,Integer> {

    Agent findAgentById (Integer id);


}
