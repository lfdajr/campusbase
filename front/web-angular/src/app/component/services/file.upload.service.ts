import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ErrorService } from '../../common/service/error.service';
import { Usuario } from '../../common/model/usuario';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Curso } from '../../common/model/curso';
import { Matricula } from '../../common/model/matricula';
import { Aula } from '../../common/model/aula';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {
  private urlAuth = environment.mainEndpoint;

  constructor(private http: HttpClient, private errorService: ErrorService) {
  }

  enviarArquivo() {
    
  }
}
