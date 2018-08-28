import { Component, OnInit } from '@angular/core';
import { Usuario } from '../../../common/model/usuario';
import { AuthService } from '../../../common/service/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  usuario: Usuario = new Usuario();

  constructor(private auth: AuthService) { }

  ngOnInit() {
    this.usuario = this.auth.getUsuario();
  }

}
