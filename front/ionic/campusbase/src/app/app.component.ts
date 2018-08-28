import { Component, ViewChild } from '@angular/core';

import { Events, MenuController, Nav, Platform } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';

import { Storage } from '@ionic/storage';

import { AccountPage } from '../pages/account/account';
import { LoginPage } from '../pages/login/login';
import { SignupPage } from '../pages/signup/signup';
import { TutorialPage } from '../pages/tutorial/tutorial';

import { TurmasService } from '../providers/turmas.service';
import { environment } from '../environments/environment';
import { TurmasPage } from '../pages/turmas/turmas';
import { AuthService } from '../providers/auth.service';
import { FCM } from '@ionic-native/fcm';
import { AgendaPage } from '../pages/agenda/agenda';

export interface PageInterface {
  title: string;
  name: string;
  component: any;
  icon: string;
  logsOut?: boolean;
  index?: number;
  tabName?: string;
  tabComponent?: any;
}

@Component({
  templateUrl: 'app.template.html'
})
export class CampusBaseApp {
  @ViewChild(Nav) nav: Nav;

  appPages: PageInterface[] = [
    //{ title: 'Minhas Turmas', name: 'TabsPage', component: TabsPage, tabComponent: TurmasPage, index: 0, icon: 'calendar' }
    //{ title: 'Speakers', name: 'TabsPage', component: TabsPage, tabComponent: SpeakerListPage, index: 1, icon: 'contacts' },
    //{ title: 'Map', name: 'TabsPage', component: TabsPage, tabComponent: MapPage, index: 2, icon: 'map' },
    //{ title: 'About', name: 'TabsPage', component: TabsPage, tabComponent: AboutPage, index: 3, icon: 'information-circle' }
  ];
  loggedInPages: PageInterface[] = [
    { title: 'Minhas Turmas', name: 'turmas', component: TurmasPage, icon: 'attach' },
    { title: 'Agenda', name: 'agenda', component: AgendaPage, icon: 'calendar' },
    //{ title: 'Perfil', name: 'AccountPage', component: AccountPage, icon: 'person' },
    { title: 'Logout', name: 'LoginPage', component: LoginPage, icon: 'log-out', logsOut: true }
  ];
  loggedOutPages: PageInterface[] = [
    { title: 'Login', name: 'LoginPage', component: LoginPage, icon: 'log-in' },
    { title: 'Cadastrar', name: 'SignupPage', component: SignupPage, icon: 'person-add' }
  ];
  rootPage: any;

  constructor(
    public events: Events,
    public menu: MenuController,
    public platform: Platform,
    public storage: Storage,
    public splashScreen: SplashScreen,
    public turmaService: TurmasService,
    public authService: AuthService,
    public fcm: FCM
  ) {

    if (localStorage.getItem(environment.ACCESS_TOKEN_KEY)) {
      this.rootPage = TurmasPage;
    }
    else {
      this.rootPage = LoginPage;
    }
    this.platformReady()
    this.listenToLoginEvents();
  }

  openPage(page: PageInterface) {
    let params = {};
    if (page.logsOut === true) {
      this.authService.logout();
      this.enableMenu(false);
    }
    this.nav.setRoot(page.component, params);
  }

  openTutorial() {
    this.nav.setRoot(TutorialPage);
  }

  listenToLoginEvents() {
    this.events.subscribe('user:login', () => {
      this.enableMenu(true);
    });

    this.events.subscribe('user:signup', () => {
      this.enableMenu(true);
    });

    this.events.subscribe('user:logout', () => {
      this.enableMenu(false);
    });
  }

  enableMenu(loggedIn: boolean) {
    this.menu.enable(loggedIn, 'loggedInMenu');
    this.menu.enable(!loggedIn, 'loggedOutMenu');
  }

  platformReady() {
    // Call any initial plugins when ready
    this.platform.ready().then(() => {
      this.splashScreen.hide();

      /*this.fcm.onNotification().subscribe(data => {
        if (data.wasTapped) {
          this.events.publish('turma:novaMensagem');
        } else {
          this.events.publish('turma:novaMensagem');
        };
      });*/

    });
  }

  isActive(page: PageInterface) {
    let childNav = this.nav.getActiveChildNavs()[0];

    // Tabs are a special case because they have their own navigation
    if (childNav) {
      if (childNav.getSelected() && childNav.getSelected().root === page.tabComponent) {
        return 'primary';
      }
      return;
    }

    if (this.nav.getActive() && this.nav.getActive().name === page.name) {
      return 'primary';
    }
    return;
  }
}
