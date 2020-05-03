import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { ReviewRating } from '../models/review-rating';

@Injectable({
  providedIn: 'root'
})
export class ReviewRatingService {
  private url = environment.baseUrl + 'api/reviewrating'
  private reviewrating : ReviewRating [] = [];
  constructor(
    private http: HttpClient
  ) { }
  public index() {
    const httpOptions = this.getHttpOptions();
    return this.http.get<ReviewRating[]>(this.url, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('ReviewRatingService.index: error retrieving review-rating: ' + err);
      })
    );
  }
  public findByLocation(id: number) {
    const httpOptions = this.getHttpOptions();
    return this.http.get<ReviewRating[]>(`${environment.baseUrl}api/locations/${id}/reviews/reviewratings`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('ReviewRatingService.index: error retrieving review-rating: ' + err);
      })
    );
  }
  public show(id: number) {
    const httpOptions = this.getHttpOptions();
    return this.http.get<ReviewRating>(`${this.url}/${id}`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('ReviewRatingService.show: error retrieving entry: ' + err);
      })
    );
  }
  public create(reviewrating: ReviewRating) {
   this.reviewrating.push(reviewrating);
    const httpOptions = this.getHttpOptions();
    return this.http.post<ReviewRating>(this.url, reviewrating, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('ReviewRatingService.create: error creating entry: ' + err);
      })
    );
  }
  public update(reviewrating: ReviewRating) {
    const httpOptions = this.getHttpOptions();
    return this.http
      .put<ReviewRating>(`${this.url}/${reviewrating.ratingId}`, ReviewRating, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('ReviewRatingService.update: error updating todo: ' + err);
        })
      );
  }
  public destroy(ratingId: number) {
    const httpOptions = this.getHttpOptions();
    return this.http.delete(`${this.url}/${ratingId}`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('ReviewRatingService.delete: error deleting entry: ' + err);
      })
    );
  }
  private getHttpOptions() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Athorization': 'my-auth-token'
      })
    };
    return httpOptions;
  }
}
