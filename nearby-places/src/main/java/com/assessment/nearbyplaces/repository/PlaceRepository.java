package com.assessment.nearbyplaces.repository;

import com.assessment.nearbyplaces.entity.PlaceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends CrudRepository<PlaceEntity, String> {
}
