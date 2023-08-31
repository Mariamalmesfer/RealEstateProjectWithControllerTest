package com.example.realestateprojectwithsecurity.Controller;



import com.example.realestateprojectwithsecurity.ApiResponse.ApiResponse;
import com.example.realestateprojectwithsecurity.Model.Property;
import com.example.realestateprojectwithsecurity.Model.User;
import com.example.realestateprojectwithsecurity.Service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/property")
public class PropertyController {

    private final PropertyService propertyService;


    @GetMapping("/get")
    public ResponseEntity getAllProperty() {

        return ResponseEntity.status(200).body(propertyService.getAllProperty());
    }


    @GetMapping("/get-my")
    public ResponseEntity getMyPropertys(@AuthenticationPrincipal User user)
    {
        return ResponseEntity.status(200).body(propertyService.getMyProperty(user.getId()));

    }

    @PostMapping("/add")
    public ResponseEntity addPropertys(@AuthenticationPrincipal User user, @RequestBody @Valid Property property) {
        propertyService.addProperty(user.getId(),property);
        return ResponseEntity.status(200).body(new ApiResponse("Property added"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateProperty(@AuthenticationPrincipal User user,@PathVariable Integer id, @RequestBody @Valid  Property property ){
        propertyService.updateProperty(user.getId(),id,property);
        return ResponseEntity.status(200).body(new ApiResponse("Property updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletProperty(@AuthenticationPrincipal User user,@PathVariable Integer id){
        propertyService.deleteProperty(user.getId(),id);
        return ResponseEntity.status(200).body(new ApiResponse("Property deleted"));
    }



    @GetMapping("/getbyloc/{location}")
    public ResponseEntity getbylication(@AuthenticationPrincipal User user,@PathVariable String location){
        List<Property> properties = propertyService.getbylocation(user.getId(),location);
        return ResponseEntity.status(200).body(properties);
    }


    @GetMapping("/getbysize/{size}")
    public ResponseEntity getbysize(@AuthenticationPrincipal User user,@PathVariable String size){
        List<Property> properties = propertyService.getbySize(user.getId(),size);
        return ResponseEntity.status(200).body(properties);
    }

    @GetMapping("/getbysortpriec")
    public ResponseEntity getbysortpriec(@AuthenticationPrincipal User user){
        List<Property> properties = propertyService.sortbyprice(user.getId());
        return ResponseEntity.status(200).body(properties);
    }




}
