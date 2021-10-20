package com.assessment.nearbyplaces.repository;

import com.assessment.nearbyplaces.entity.RequestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends CrudRepository<RequestEntity, Long> {
    RequestEntity findRequestEntityByLatitudeAndLongitudeAndRadius(double latitude, double longitude, double radius);
}
