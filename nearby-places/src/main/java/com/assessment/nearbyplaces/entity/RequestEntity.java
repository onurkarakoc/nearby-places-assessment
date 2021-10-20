package com.assessment.nearbyplaces.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "request")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_id")
    private Long id;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "radius")
    private double radius;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "request_places",
            joinColumns = {
                    @JoinColumn(name = "request_id", referencedColumnName = "request_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "place_id", referencedColumnName = "place_id")
            }
    )
    private List<PlaceEntity> placeEntities;

    public RequestEntity(double latitude, double longitude, double radius, List<PlaceEntity> placeEntities) {
        this.latitude=latitude;
        this.longitude=longitude;
        this.radius=radius;
        this.placeEntities=placeEntities;
    }
}
