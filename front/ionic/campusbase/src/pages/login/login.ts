import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

import { NavController, AlertController } from 'ionic-angular';

import { UserOptions } from '../../interfaces/user-options';

import { TabsPage } from '../tabs-page/tabs-page';
import { SignupPage } from '../signup/signup';
import { AuthService } from '../../providers/auth.service';
import { TurmasPage } from '../turmas/turmas';


@Component({
  selector: 'page-user',
  templateUrl: 'login.html'
})
export class LoginPage {
  login: UserOptions = { username: localStorage.getItem("username"), password: '' };
  submitted = false;

  constructor(public navCtrl: NavController, 
    private authService: AuthService,
    public alertCtrl: AlertController) { }

  onLogin(form: NgForm) {
    this.submitted = true;

    if (form.valid) {
      this.authService.login(this.login).subscribe(_token => {
        if (_token) {
          localStorage.setItem("username", this.login.username);
          this.navCtrl.setRoot(TurmasPage);
        }
      },
        error => {
          const alert = this.alertCtrl.create({
            title: 'Problema no login',
            subTitle: 'Email ou senha não conferem!',
            buttons: ['OK']
          });
          alert.present();
        });

    }
  }

  onSignup() {
    this.navCtrl.push(SignupPage);
  }
}
