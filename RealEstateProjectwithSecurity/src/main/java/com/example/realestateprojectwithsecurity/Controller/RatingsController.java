package com.example.realestateprojectwithsecurity.Controller;


import com.example.realestateprojectwithsecurity.ApiResponse.ApiResponse;
import com.example.realestateprojectwithsecurity.Model.Ratings;
import com.example.realestateprojectwithsecurity.Model.User;
import com.example.realestateprojectwithsecurity.Service.RatingsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ratings")
public class RatingsController {

    private final RatingsService ratingsService;



    @GetMapping("/get")
    public ResponseEntity getAllratings() {

        return ResponseEntity.status(200).body(ratingsService.getrateing());
    }


    @PostMapping("/add/{pid}")
    public ResponseEntity addratings(@AuthenticationPrincipal User user,@RequestBody @Valid Ratings ratings , @PathVariable  Integer pid) {
        ratingsService.addRarting(user.getId(),ratings,pid);
        return ResponseEntity.status(200).body(new ApiResponse("Rating added"));

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletRating(@AuthenticationPrincipal User user,@PathVariable Integer id){
        ratingsService.deleteRating(user.getId(),id);
        return ResponseEntity.status(200).body(new ApiResponse("Rating deleted"));
    }


    @GetMapping("/rating/{id}")
    public ResponseEntity ratingbyid(@AuthenticationPrincipal User user,@PathVariable Integer id){
        return ResponseEntity.status(200).body(ratingsService.getraitinbyid(user.getId(),id));
    }














}
