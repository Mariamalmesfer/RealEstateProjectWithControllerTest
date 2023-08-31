package com.example.realestateprojectwithsecurity.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@RequiredArgsConstructor
public class Property {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "Title  is required")
    @Column(columnDefinition = "varchar(5) not null")
    private String title;


    @NotEmpty(message = "Description is required")
    @Pattern(regexp = "^(Buy|Rent)$" , message = "must be for  buy or rent")
    private String description;


    @NotEmpty(message = "Property size is required")
    @Pattern(regexp ="^[0-9]+\\.[0-9]+m$" , message = "Invalid property size format. Must be in meters.")
    private String size;



    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be a positive number")
    private Double price;


    @NotEmpty(message = "Location is required")
    private String location;

    @NotNull(message = "Number of bedrooms is required")
    @Min(value = 0, message = "Number of bedrooms must be a positive number")
    //@Column(columnDefinition = "not null")
    private Integer bedrooms;

    @NotNull(message = "Number of bathrooms is required")
    @Min(value = 0, message = "Number of bathrooms must be a positive number")
    private Integer bathrooms;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "agent_id", referencedColumnName = "User_id")
    private Agent agent;



    @OneToOne(cascade =CascadeType.ALL,mappedBy = "property")
    @PrimaryKeyJoinColumn
    Deal deal;


    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "property")
    private Set<Ratings> ratings;
}
