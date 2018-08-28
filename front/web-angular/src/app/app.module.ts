import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './/app-routing.module';
import { LoginPage } from './pages/login/login.page';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AuthService } from './common/service/auth.service';
import { HttpClientModule } from '@angular/common/http';
import { JwtHelperService, JwtModule } from '@auth0/angular-jwt';
import { environment } from '../environments/environment';
import { ErrorService } from './common/service/error.service';
import { TurmasModule } from './pages/turmas/turmas.module';
import { PageNotFoundComponent } from './pages/notfound/not-found.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    LoginPage,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        authScheme: 'Bearer ',
        whitelistedDomains: environment.whitelistedDomains
      }
    }),
    NgbModule.forRoot(),
    TurmasModule,
    AppRoutingModule
  ],
  providers: [
    AuthService,
    JwtHelperService,
    ErrorService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

export function tokenGetter() {
  return localStorage.getItem('access_token');
}