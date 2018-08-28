import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { NavController } from 'ionic-angular';
import { Usuario } from '../../model/usuario';
import { TurmasPage } from '../turmas/turmas';
import { UserService } from '../../providers/user.service';
import { AuthService } from '../../providers/auth.service';
import { LoginPage } from '../login/login';


@Component({
  selector: 'sign-up',
  templateUrl: 'signup.html'
})
export class SignupPage {
  usuario: Usuario = new Usuario();
  submitted = false;

  constructor(public navCtrl: NavController, public usuarioService: UserService, public auth: AuthService) {}

  onSignup(form: NgForm) {
    this.submitted = true;

    if (form.valid) {
      this.usuario.nomeCompleto = this.usuario.nome;
      this.usuarioService.novoUsuario(this.usuario).subscribe(_ret => {
        
          this.auth.login({username: this.usuario.email, password: this.usuario.senha}).subscribe(_token => {
            this.navCtrl.push(TurmasPage);
          });
      });
      
    }
  }
}
