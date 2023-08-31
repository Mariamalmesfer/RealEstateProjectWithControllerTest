package com.example.realestateprojectwithsecurity.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;



@Data
@AllArgsConstructor
@Entity
@RequiredArgsConstructor
public class Ratings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String review;

    @NotNull
    @Max(value = 10 , message = "the max rating is 10")
    @Min(value = 1,message = "the min rating is 1")
    private Integer rating;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "property_id",referencedColumnName = "id")
    private Property property;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id", referencedColumnName = "User_id")
    private Customer customer;

}
