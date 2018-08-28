import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Curso } from '../model/curso';
import { Agenda } from '../model/agenda';
import { Aula } from '../model/aula';
import { Mensagem } from '../model/mensagem';
import { ErrorService } from './error.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class AgendaService {
  private urlAuth = environment.mainEndpoint;

  constructor(private http: HttpClient, private errorService: ErrorService) {
  }

  minhaAgenda(): Observable<Agenda[]> {
    return this.http.get<Agenda[]>(`${this.urlAuth}/agenda`, httpOptions).pipe(
      catchError(this.errorService.handleError<any>('agenda'))
    );
  }

}
