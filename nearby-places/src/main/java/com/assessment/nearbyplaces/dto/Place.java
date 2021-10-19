package com.assessment.nearbyplaces.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Place {

    private String place_id;
    private double latitude;
    private double longitude;
    private String name;
}
