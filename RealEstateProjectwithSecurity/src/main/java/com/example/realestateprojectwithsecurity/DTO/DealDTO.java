package com.example.realestateprojectwithsecurity.DTO;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Valid
public class DealDTO {


    private Integer property_id;

    private String description;

    private Double price;
}
