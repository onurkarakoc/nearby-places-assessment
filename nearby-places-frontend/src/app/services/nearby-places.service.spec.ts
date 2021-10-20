import { TestBed } from '@angular/core/testing';

import { NearbyPlacesService } from './nearby-places.service';

describe('NearbyPlacesService', () => {
  let service: NearbyPlacesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NearbyPlacesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
