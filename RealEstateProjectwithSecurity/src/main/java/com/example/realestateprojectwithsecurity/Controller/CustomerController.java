package com.example.realestateprojectwithsecurity.Controller;

import com.example.realestateprojectwithsecurity.ApiResponse.ApiResponse;
import com.example.realestateprojectwithsecurity.DTO.AgentDTO;
import com.example.realestateprojectwithsecurity.DTO.CustomerDTO;
import com.example.realestateprojectwithsecurity.Service.AgentService;
import com.example.realestateprojectwithsecurity.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;


    @PostMapping("/regester")
    public ResponseEntity Regester(@RequestBody @Valid CustomerDTO customerDTO) {
        customerService.regester(customerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("customer added"));

    }

    @PostMapping("/logout")
    public ResponseEntity Logoout() {
        return ResponseEntity.status(200).body(new ApiResponse("Log out successful"));

    }
}
