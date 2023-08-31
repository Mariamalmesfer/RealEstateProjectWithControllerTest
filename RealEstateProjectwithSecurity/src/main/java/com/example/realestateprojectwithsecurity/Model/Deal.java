package com.example.realestateprojectwithsecurity.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@RequiredArgsConstructor
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Description is required")
    private String description;

    @NotNull(message = "Deal price is required")
    @Min(value = 0, message = "Deal price must be a positive value")
    private Double price;


    private boolean agreement;


    @OneToOne
    @MapsId
    @JsonIgnore
    Property property;




    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "agent_id", referencedColumnName = "User_id")
    private Agent agent;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id", referencedColumnName = "User_id")
    private Customer customer;


}
