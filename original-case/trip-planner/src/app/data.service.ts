import { Injectable } from '@angular/core';
import { HttpClient, 
         HttpErrorResponse,
         HttpParams,
         HttpHeaders } from '@angular/common/http';

import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private REST_API_SERVER = "http://172.242.224.3:8080"
  private AIRPORTS = "/airports";
  private FARES = "/fares";
  
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'Basic ' + btoa('user:secret123'), 
    })
  };

  constructor(private httpClient: HttpClient) { }

  handleError(error: HttpErrorResponse) {
    let errorMessage = 'Unknown error!';
    if (error.error instanceof ErrorEvent) {
      // Client-side errors
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Server-side errors
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }

  public getAirports() {
    // const options = { params: new HttpParams({fromString: "currency=EUR"}) };
    return this.httpClient
               .get(this.REST_API_SERVER + this.AIRPORTS + "?size=1500", this.httpOptions)
               .pipe(catchError(this.handleError));
//               .map((response : any) => response.json().data as Airport);
  }

  public getFare(origin: string, destination: string) {
    // const options = { params: new HttpParams({fromString: "currency=EUR"}) };
    const apiURL = this.REST_API_SERVER + this.FARES + `/${origin}/${destination}`;
    console.log("API URL: " + apiURL);
    return this.httpClient
               .get(apiURL, this.httpOptions)
               .pipe(catchError(this.handleError));
  }
}
