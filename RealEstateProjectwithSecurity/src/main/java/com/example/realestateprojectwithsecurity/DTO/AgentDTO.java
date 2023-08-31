package com.example.realestateprojectwithsecurity.DTO;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Valid
public class AgentDTO {

    private Integer User_id;


    private String username;

    private String password;

    private String role;

}
