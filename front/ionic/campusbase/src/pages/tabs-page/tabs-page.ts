import { Component } from '@angular/core';

import { NavParams, Events } from 'ionic-angular';

import { AboutPage } from '../about/about';
import { TurmasPage } from '../turmas/turmas';
import { environment } from '../../environments/environment';

@Component({
  templateUrl: 'tabs-page.html'
})
export class TabsPage {
  // set the root pages for each tab
  tab1Root: any = TurmasPage;
  tab4Root: any = AboutPage;
  mySelectedIndex: number;

  constructor(navParams: NavParams, public events: Events) {
    this.mySelectedIndex = navParams.data.tabIndex || 0;

    if (localStorage.getItem(environment.ACCESS_TOKEN_KEY)) {
      this.events.publish("user:login");
    }
  }

}
