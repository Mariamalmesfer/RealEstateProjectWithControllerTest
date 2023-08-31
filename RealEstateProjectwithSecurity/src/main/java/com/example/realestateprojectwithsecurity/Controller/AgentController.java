package com.example.realestateprojectwithsecurity.Controller;


import com.example.realestateprojectwithsecurity.ApiResponse.ApiResponse;
import com.example.realestateprojectwithsecurity.DTO.AgentDTO;
import com.example.realestateprojectwithsecurity.Model.Agent;
import com.example.realestateprojectwithsecurity.Service.AgentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/agent")
public class AgentController {

       private final AgentService agentService;


    @PostMapping("/regester")
    public ResponseEntity Regester(@RequestBody @Valid AgentDTO agent) {
        agentService.regester(agent);
        return ResponseEntity.status(200).body(new ApiResponse("Agent added"));

    }

    @PostMapping("/logout")
    public ResponseEntity Logoout() {
        return ResponseEntity.status(200).body(new ApiResponse("Log out successful"));

    }


}
