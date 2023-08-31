package com.example.realestateprojectwithsecurity.Controller;


import com.example.realestateprojectwithsecurity.ApiResponse.ApiResponse;
import com.example.realestateprojectwithsecurity.DTO.DealDTO;
import com.example.realestateprojectwithsecurity.Model.User;
import com.example.realestateprojectwithsecurity.Service.DealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/deal")
public class DealController {

    private final DealService dealService ;

    @GetMapping("/get-all")
    public ResponseEntity getAllDeals() {

        return ResponseEntity.status(200).body(dealService.getAllDeals());
    }

    @GetMapping("/get-agent")
    public ResponseEntity getAgentDeals(@AuthenticationPrincipal User user)
    {
        return ResponseEntity.status(200).body(dealService.getAgentDeals(user.getId()));

    }


    @GetMapping("/get-coustomesr")
    public ResponseEntity getCoustomesrDeals(@AuthenticationPrincipal User user)
    {
        return ResponseEntity.status(200).body(dealService.getCoustomesrtDeals(user.getId()));

    }


    @PostMapping("/add/{username}")
    public ResponseEntity addDeals(@AuthenticationPrincipal User user,@RequestBody @Valid DealDTO dealDTO , @PathVariable String username) {
        dealService.addDeal(user.getId(),dealDTO,username);
        return ResponseEntity.status(200).body(new ApiResponse("Deal added"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateDeals(@AuthenticationPrincipal User user,@PathVariable Integer id, @RequestBody @Valid  DealDTO dealDTO ){
        dealService.updateDeal(user.getId(),id,dealDTO );
        return ResponseEntity.status(200).body(new ApiResponse("Deal updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletDeal(@AuthenticationPrincipal User user,@PathVariable Integer id){
        dealService.deleteDeal(user.getId(),id);
        return ResponseEntity.status(200).body(new ApiResponse("Deal deleted"));
    }



}
