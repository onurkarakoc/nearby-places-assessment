package com.assessment.nearbyplaces.controller;

import com.assessment.nearbyplaces.dto.Place;
import com.assessment.nearbyplaces.service.NearbyPlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class NearbyPlaceController {

    private NearbyPlaceService service;

    public NearbyPlaceController(NearbyPlaceService service) {
        this.service = service;
    }

    @GetMapping("/nearby-places")
    public ResponseEntity<ArrayList<Place>> findNearbyPlaces(@RequestParam double latitude, @RequestParam double
            longitude, @RequestParam double radius) throws Exception {
        ArrayList<Place> placesList = service.findNearbyPlaces(latitude, longitude, radius);
        return new ResponseEntity<>(placesList, HttpStatus.OK);
    }
}
