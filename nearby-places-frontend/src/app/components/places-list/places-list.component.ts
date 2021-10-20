import { Component, OnInit } from '@angular/core';
import { NearbyPlacesService } from 'src/app/services/nearby-places.service';
import { Request } from 'src/app/models/request.model';
import { Router } from '@angular/router';
import { Place } from 'src/app/models/place.model';

@Component({
  selector: 'app-places-list',
  templateUrl: './places-list.component.html',
  styleUrls: ['./places-list.component.css']
})
export class PlacesListComponent implements OnInit {

  request: Request = new Request();
  places: Place[];
  show = false;

  markers: any;

  mapOptions: google.maps.MapOptions = {
    center: { lat: 40, lng: 32 },
    zoom: 1
  };

  constructor(private nearbyPlacesService: NearbyPlacesService,
    private router: Router) { }

  ngOnInit(): void {
  }

  getPlaces() {
    this.nearbyPlacesService.getAll(this.request).subscribe(data => {
      console.log(data);
      this.places = data;
      this.markers = [];
      for (let idx in this.places) {
        this.markers.push({
          position: {
            lat: this.places[idx].latitude,
            lng: this.places[idx].longitude
          }
        })
      }
      this.show = true;
    },
      error => console.log(error));
  }

  onSubmit() {
    this.getPlaces();
  }

}
