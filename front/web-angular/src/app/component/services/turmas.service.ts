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
export class TurmasService {
  private urlAuth = environment.mainEndpoint;
  turmaAberta: Curso;

  constructor(private http: HttpClient, private errorService: ErrorService) {
  }

  minhasTurmas(): Observable<Curso[]> {
    return this.http.get<Curso[]>(`${this.urlAuth}/turmas`, httpOptions).pipe(
      tap(_ret => {
        //console.log("buscando turmas...");
        //console.log(_ret);
      }),
      catchError(this.errorService.handleError<any>('minhasTurmas'))
    );
  }

  detalharTurma(cursoId: number): Observable<Matricula> {
    return this.http.get<Matricula>(`${this.urlAuth}/turma/${cursoId}`, httpOptions).pipe(
      tap(_ret => {
        this.turmaAberta = _ret.curso;
        //console.log("buscando turmas...");
        console.log(_ret);
      }),
      catchError(this.errorService.handleError<any>('detalharTurma'))
    );
  }

  detalharAula(aulaId: number, cursoId: number): Observable<Aula> {
    return this.http.get<Aula>(`${this.urlAuth}/turma/${cursoId}/aula/${aulaId}`, httpOptions).pipe(
      tap(_ret => {
        //console.log("buscando turmas...");
        console.log(_ret);
      }),
      catchError(this.errorService.handleError<any>('detalharAula'))
    );
  }

  novaTurma(curso: Curso) : Observable<Curso> {
    return this.http.post<Curso>(`${this.urlAuth}/turma`, curso, httpOptions).pipe(
      tap(_ret => {
        console.log(_ret);
      }),
      catchError(this.errorService.handleError<any>('novaTurma'))
    );
  }

  novaAula(aula: Aula): Observable<Aula> {
    return this.http.post<Aula>(`${this.urlAuth}/turma/${aula.curso.id}/aula`, aula, httpOptions).pipe(
      tap(_ret => {
        console.log(_ret);
      }),
      catchError(this.errorService.handleError<any>('novaAula'))
    );
  }

  baixarArquivo(filename: string): Observable<Blob>
  {
    return this.http.get<Blob>(`${this.urlAuth}/turma/aula/file/${filename}`, {responseType: "blob" as "json"}).pipe(
      catchError(this.errorService.handleError<any>('download error'))
    );
  }

  buscarMatriculados(cursoId: number) : Observable<Matricula[]> {
    return this.http.get<Matricula[]>(`${this.urlAuth}/turma/${cursoId}/configuracoes`, httpOptions).pipe(
      catchError(this.errorService.handleError<any>('buscarMatriculados error'))
    );    
  }
}
