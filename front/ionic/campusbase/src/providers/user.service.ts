import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ErrorService } from './error.service';
import { environment } from '../environments/environment';
import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Usuario } from "../model/usuario";

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

@Injectable()
export class UserService {

    constructor(public http: HttpClient, public errorService: ErrorService) {
    }

    novoUsuario(usuario: Usuario) :Observable<Usuario> {
        return this.http.post<Usuario>(`${environment.mainEndpoint}/newUser`, usuario, httpOptions).pipe(
            catchError(this.errorService.handleError<any>('novoUsuario'))
          );
    }

}