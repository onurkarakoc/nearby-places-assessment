package com.assessment.nearbyplaces.service;

import com.assessment.nearbyplaces.dto.Place;
import com.assessment.nearbyplaces.entity.PlaceEntity;
import com.assessment.nearbyplaces.entity.RequestEntity;
import com.assessment.nearbyplaces.repository.PlaceRepository;
import com.assessment.nearbyplaces.repository.RequestRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NearbyPlaceService {

    @Value("${google.places.api.url}")
    private String url;
    @Value("${google.places.api.key}")
    private String api_key;
    private ObjectMapper objectMapper;
    private RequestRepository requestRepository;
    private PlaceRepository placeRepository;

    public NearbyPlaceService(RequestRepository requestRepository, PlaceRepository placeRepository) {
        this.requestRepository=requestRepository;
        this.placeRepository=placeRepository;
    }

    public ArrayList<Place> findNearbyPlaces(double latitude, double longitude, double radius) throws Exception {
        RequestEntity entity = requestRepository.findRequestEntityByLatitudeAndLongitudeAndRadius(latitude, longitude,radius);
        if (entity != null) {
            return convertPlaceEntityToPlace(entity.getPlaceEntities());
        }
        else {
            objectMapper = new ObjectMapper();
            // This location format is specified for Google Places API
            // Usage: latitude,longitude -> %2C means ,
            String location = latitude + "%2C" + longitude;
            String nearbyPlacesUrl = url + "location=" + location + "&" + "radius=" + radius + "&" + "key=" + api_key;
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(nearbyPlacesUrl)
                    .method("GET", null)
                    .build();
            // Coming response from Google places api
            Response response = client.newCall(request).execute();
            // Google places api can throw an exception which is called IO Exception; so check that
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            // Places node contains place objects coming from api
            JsonNode placesNode = objectMapper.readTree(response.body().string()).get("results");
            ArrayList<Place> placesList = createPlaces(placesNode);
            requestRepository.save(new RequestEntity(latitude, longitude, radius, convertPlaceToPlaceEntity(placesList) ));
            return placesList;
        }
    }

    // This function takes places node as parameter and parse it.
    // Creates dt objects and adds to array list.
    private ArrayList<Place> createPlaces(JsonNode placesNode) {
        ArrayList<Place> placesList = new ArrayList<>();
        for (JsonNode place : placesNode) {
            // Reads location node inside geometry node.
            JsonNode location = place.get("geometry").get("location");
            placesList.add(new Place(place.get("place_id").toString(), location.get("lat").doubleValue(),
                    location.get("lng").doubleValue(), place.get("name").toString()));
        }
        return placesList;
    }

    private List<PlaceEntity> convertPlaceToPlaceEntity(ArrayList<Place> placeList) {
        List<PlaceEntity> entities = new ArrayList<>();
        for (Place place : placeList) {
            entities.add(new PlaceEntity(place.getPlace_id(), place.getLatitude(), place.getLongitude(), place.getName()));
        }
        return entities;
    }

    private ArrayList<Place> convertPlaceEntityToPlace(List<PlaceEntity> entities) {
        ArrayList<Place> places = new ArrayList<>();
        for (PlaceEntity entity : entities) {
            places.add(new Place(entity.getPlaceId(), entity.getLatitude(), entity.getLongitude(), entity.getName()));
        }
        return places;
    }
}
