package com.assessment.nearbyplaces.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "place")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PlaceEntity {
    @Id
    @Column(name = "place_id")
    private String placeId;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "placeEntities")
    private List<RequestEntity> requestEntities;

    public PlaceEntity(String place_id, double latitude, double longitude, String name) {
        this.placeId=place_id;
        this.latitude=latitude;
        this.longitude=longitude;
        this.name=name;
    }
}
