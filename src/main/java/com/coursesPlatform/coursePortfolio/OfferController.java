package com.coursesPlatform.coursePortfolio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offer")
@CrossOrigin(origins = "http://localhost:4200/")
class OfferController {

    private final OfferService offerService;

    OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/{mail}")
    ResponseEntity<List<OfferDTO>> getOffersOfCustomer(@PathVariable("mail") String mail) {
        List<OfferDTO> allOffersOfCustomer = offerService.findAllOffersOfCustomer(mail);
        return new ResponseEntity<>(allOffersOfCustomer, HttpStatus.OK);
    }

    @PostMapping("/add")
    ResponseEntity<OfferDTO> createOffer(@RequestBody OfferDTO offerDTO) {
        OfferDTO createdOffer = offerService.create(offerDTO);
        return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
    }
}
