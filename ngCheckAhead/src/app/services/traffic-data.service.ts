import { TrafficData } from './../models/traffic-data';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { PopulartimesData } from '../models/populartimes-data';

@Injectable({
  providedIn: 'root'
})
export class TrafficDataService {
    serverData:JSON;

  constructor(private http: HttpClient) { }


  public getTransitData(googleId: string) {
    const proxyurl = "https://cors-anywhere.herokuapp.com/";
    const httpOptions = this.getHttpOptions();
    return this.http.get<TrafficData>(proxyurl + 'http://18.222.135.147/' + googleId, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error getting  transit data from api: ' + err);
      })
    );
  }

  private getHttpOptions() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        // 'Access-Control-Allow-Origin': '*'
      })
    };
    return httpOptions;
  }


}
