import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Location } from 'src/app/models/location';
import { environment } from '../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  
  private baseUrl: string = '';
  
  constructor(private httpClient: HttpClient) { 
    this.baseUrl = `${environment.locationUrl}`
  }

  addLocation(location: Location): Observable<any>{
    const url = `${this.baseUrl}/newLocation`;
    return this.httpClient.post(url, location);
  }

  getAllLocations(id: string):Observable<any>{
    const url = `${this.baseUrl}/getAll/${id}`;
    return this.httpClient.get(url);
  }
}
