import { Component, OnInit, Input } from '@angular/core';
import { Usuario } from '../../../../common/model/usuario';

@Component({
  selector: 'app-header-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.scss']
})
export class UserMenuComponent implements OnInit {

  _usuario: Usuario;
  
  @Input()
  set usuario(usuario: Usuario) {
    this._usuario = usuario;
  }

  get usuario() : Usuario {
    return this._usuario;
  }

  constructor() { }

  ngOnInit() {
    
  }

}
