import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';

import { catchError, tap } from 'rxjs/operators';
import { environment } from '../environments/environment';
import { Usuario } from '../model/usuario';
import { DadosToken } from '../model/dadosToken';

import { Observable, ReplaySubject } from 'rxjs';
import { ErrorService } from './error.service';
import { Events } from 'ionic-angular';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class AuthService {
  private urlAuth = environment.mainEndpoint + "/auth/login";
  private validTokenObserver: ReplaySubject<TokenChecker>;
  private usuarioLogado: Usuario;

  constructor(
    private http: HttpClient,
    private jwtHelperService: JwtHelperService,
    public events: Events,
    private errorService: ErrorService) {
  }

  login(credentials: {username: string; password: string}): Observable<Token> {
    return this.http.post<any>(this.urlAuth, credentials, httpOptions).pipe(
      tap(_ret => {
        this.armazenarToken(_ret);
        this.events.publish('user:login');
        }),
      catchError(this.errorService.handleError2)
    );
  }

    private defineInValidTokenAtLogout() {
    const validToken: TokenChecker = new TokenChecker();
    validToken.valido = false;
    this.validTokenObserver.next(validToken);
  }

  logout() {
    this.clearLocalStorage();
    const logoutMetodo = 'logout';
    //const retorno = this.http.post<any>(this.urlAuth + logoutMetodo, httpOptions);
    //return retorno;
  }

  logoutFrontEnd() {
    this.clearLocalStorage();
    this.defineInValidTokenAtLogout();
  }

  isInternallyAuthenticated(): boolean {
    if (this.isAuthenticated()) {
      return this.isInternalUser();
    } else {
      return false;
    }
  }

  isExternallyAuthenticated(): boolean {
    if (this.isAuthenticated()) {
      return !this.isInternalUser();
    } else {
      return false;
    }
  }

  isInternalUser(): boolean {
    const decodedToken = this.getDecodedToken();
    if (decodedToken && decodedToken.params.userType === 'internal') {
      return true;
    }
    return false;
  }

  isAuthenticated(): boolean {
    return true;
    //TODO tratar autenticacao localmente
    /*const token = localStorage.getItem('access_token');
    if (token) {
      if (this.tokenChecker != null) {
        return this.tokenChecker.valido;
      }
      return true;
    } else {
      return false;
    }*/
  }

  private startTimeToken() {
    localStorage.setItem(
      'start_time',
      Date.now()
        .valueOf()
        .toString()
    );
  }

  private clearLocalStorage() {
    localStorage.removeItem(environment.ACCESS_TOKEN_KEY);
    localStorage.removeItem('last_request');
    localStorage.removeItem('start_time');
  }

  public getDadosToken(): DadosToken {
    const dadosToken: DadosToken = {};
    if (this.isAuthenticated()) {
      const usr = new Usuario();
      const decodedToken = this.getDecodedToken();
      usr.cpf = decodedToken.identity;
      usr.nome = decodedToken.name;
      usr.email = decodedToken.params.Email;
      dadosToken.usuario = usr;
      //dadosToken.idProtocolo = decodedToken.params.idp;
      //dadosToken.isDenuncia = decodedToken.params.isd === 'true';
    }
    return dadosToken;
  }

  /*private refreshToken(): Observable<Token> {
    if (this.isAuthenticated()) {
      const path = 'refresh';
      return this.http.get<Token>(this.urlAuth + path, httpOptions).pipe(
        tap(token => {
          this.armazenarToken(token.key);
        })
      );
    }
  }*/

  private armazenarToken(tokens: any) {
    localStorage.setItem(environment.ACCESS_TOKEN_KEY, tokens.token);
    localStorage.setItem('refresh_token', tokens.refreshToken);
    this.startTimeToken();
  }

  getUsuario(): Usuario {
    if (!this.isAuthenticated()) {
      console.log('Usuário não autenticado.');
      return;
    }

    if (!this.usuarioLogado)
    {
      this.usuarioLogado = new Usuario();
      const decodedToken = this.getDecodedToken();

      if (!decodedToken)
        return null;

      this.usuarioLogado.nome = decodedToken.username;
      this.usuarioLogado.email = decodedToken.sub;
      this.usuarioLogado.id = decodedToken.id;
    }
    
    return this.usuarioLogado;
  }

  getPerfils() {
    const decodedToken = this.getDecodedToken();
    return decodedToken ? decodedToken.roles : null;
  }

  private getDecodedToken() {
    const token = localStorage.getItem('access_token');
    return token ? this.jwtHelperService.decodeToken(token) : null;
  }


  /**
   * Para evitar trabalhar com a data e horário local da máquina na validação da expiração do token no frontend;
   */
  /*checkValidToken(): Observable<any> {
    const path = 'isValid';
    return this.http.get<TokenChecker>(this.urlAuth + path).pipe(
      tap(response => {
        this.validTokenObserver.next(response);
      })
    );
  }*/

  
}

export class Token {
  key: string;
  type: string;
}

export class ResponseLongExternalUser {
  key: string;
  type: string;
  idDen: number;
  idReg: number;
}

export class TokenChecker {
  valido: boolean;
  checkTime: any;
}
