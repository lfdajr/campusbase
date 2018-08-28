import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TipoMenu } from '../../../component/page-components/context-menu/context-menu.component';

@Component({
  templateUrl: './turmas-listagem.page.html',
  styleUrls: ['./turmas-listagem.page.scss']
})
export class TurmasListagemPage implements OnInit {

  tipo: TipoMenu = TipoMenu.LISTAGEM_TURMAS;

  constructor(private router: Router) { }

  ngOnInit() {
  }

  routerTo(evtResp) {
    switch (evtResp.evt) {
      case "detalharCurso" :
        console.log("auqi " + evtResp.id ) ;
        this.router.navigate([`turma/${evtResp.id}`]);
      break;
    }
  }

}
