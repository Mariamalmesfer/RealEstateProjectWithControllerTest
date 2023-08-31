package com.example.realestateprojectwithsecurity.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@RequiredArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToOne
    @MapsId
    @JsonIgnore
    User user;


    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "customer")
    private Set<Deal> deals;

    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "customer")
    private Set<Ratings> ratings;




}
