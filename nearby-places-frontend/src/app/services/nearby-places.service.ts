import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Place } from '../models/place.model';
import { Request } from '../models/request.model';

const baseUrl = 'http://localhost:8070/nearby-places';

@Injectable({
  providedIn: 'root'
})
export class NearbyPlacesService {

  constructor(private http: HttpClient) { }

  getAll(request: Request): Observable<Place[]> {
    const params = new HttpParams().append('latitude', request.latitude!).append('longitude', request.longitude!).append('radius', request.radius!);
    return this.http.get<Place[]>(baseUrl, { params });
  }
}