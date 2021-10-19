import { Component, OnInit } from '@angular/core';
import { NearbyPlacesService } from 'src/app/services/nearby-places.service';
import { Request } from 'src/app/models/request.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-places-list',
  templateUrl: './places-list.component.html',
  styleUrls: ['./places-list.component.css']
})
export class PlacesListComponent implements OnInit {

  request: Request = new Request();

  constructor(private nearbyPlacesService: NearbyPlacesService,
    private router: Router) { }

  ngOnInit(): void {
  }

  getPlaces() {
    this.nearbyPlacesService.getAll(this.request).subscribe(data => {
      console.log(data);
      this.goToPlacesList();
    },
      error => console.log(error));
  }

  goToPlacesList() {
    this.router.navigate(['/places']);
  }

  onSubmit() {
    console.log(this.request.latitude);
    console.log(this.request.longitude);
    console.log(this.request.radius);
    this.getPlaces();
  }

}
