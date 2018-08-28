import { Injectable } from '@angular/core';

import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';

@Injectable()
export class ErrorService {

  constructor() { }

  handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      alert("erro: " + operation);
      console.log(`Erro: ${operation}`);
      console.log(error);
      return of(result as T);
    };
  }
}
