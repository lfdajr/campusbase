import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable()
export class ErrorService {

  constructor() { }

  handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      alert("erro: " + operation);
      console.log(`Erro: ${operation}`);
      console.log(error);
      return Observable.of(result as T);
    };
  }

  handleError2(error: HttpErrorResponse) {
    console.log(error);
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.log('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.log(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return Observable.throw(
      'Something bad happened; please try again later.');
  };
}
