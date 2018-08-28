import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { NgModule, ErrorHandler } from '@angular/core';

import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';

import { InAppBrowser } from '@ionic-native/in-app-browser';
import { SplashScreen } from '@ionic-native/splash-screen';

import { IonicStorageModule } from '@ionic/storage';

import { CampusBaseApp } from './app.component';

import { AboutPage } from '../pages/about/about';
import { PopoverPage } from '../pages/about-popover/about-popover';
import { AccountPage } from '../pages/account/account';
import { LoginPage } from '../pages/login/login';
import { SignupPage } from '../pages/signup/signup';
import { TabsPage } from '../pages/tabs-page/tabs-page';
import { TutorialPage } from '../pages/tutorial/tutorial';

import { JwtHelperService, JwtModule } from '@auth0/angular-jwt';
import { AuthService } from '../providers/auth.service';
import { ErrorService } from '../providers/error.service';
import { TurmasService } from '../providers/turmas.service';
import { AgendaService } from '../providers/agenda.service';
import { environment } from '../environments/environment';
import { TurmasMensagensPage } from '../pages/turmas-mensagens/turmas-mensagens';
import { TurmasPage } from '../pages/turmas/turmas';
import { UserService } from '../providers/user.service';

import { FCM } from '@ionic-native/fcm';
import { AgendaPage } from '../pages/agenda/agenda';
import { AgendaDetalhePage } from '../pages/agenda-detalhe/agenda-detalhe';

@NgModule({
  declarations: [
    CampusBaseApp,
    AboutPage,
    AccountPage,
    LoginPage,
    PopoverPage,
    TurmasPage,
    TurmasMensagensPage,
    SignupPage,
    AgendaPage,
    AgendaDetalhePage,
    TabsPage,
    TutorialPage
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    IonicModule.forRoot(CampusBaseApp, {}, {
      links: [
        //{ component: TabsPage, name: 'TabsPage', segment: 'tabs-page' },
        { component: LoginPage, name: 'LoginPage', segment: '' },
        { component: TurmasPage, name: 'Turmas', segment: 'turmas' },
        { component: TurmasMensagensPage, name: 'TurmasMensagens', segment: 'mensagens' },
        { component: AboutPage, name: 'About', segment: 'about' },
        { component: TutorialPage, name: 'Tutorial', segment: 'tutorial' },
        { component: AgendaPage, name: 'Agenda', segment: 'agenda' },
        { component: AccountPage, name: 'AccountPage', segment: 'account' },
        { component: SignupPage, name: 'SignupPage', segment: 'signup' }
      ]
    }),
    IonicStorageModule.forRoot(),
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        authScheme: 'Bearer ',
        whitelistedDomains: environment.whitelistedDomains
      }
    })
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    CampusBaseApp,
    AboutPage,
    AccountPage,
    LoginPage,
    PopoverPage,
    SignupPage,
    TabsPage,
    TurmasPage,
    AgendaPage,
    AgendaDetalhePage,
    TurmasMensagensPage,
    TutorialPage
  ],
  providers: [
    { provide: ErrorHandler, useClass: IonicErrorHandler },
    JwtHelperService,
    AuthService,
    ErrorService,
    TurmasService,
    AgendaService,
    UserService,
    InAppBrowser,
    SplashScreen,
    FCM
  ]
})
export class AppModule { }

export function tokenGetter() {
  return localStorage.getItem(environment.ACCESS_TOKEN_KEY);
}