import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { TurmasService } from '../../../component/services/turmas.service';
import { TipoMenu } from '../../../component/page-components/context-menu/context-menu.component';

@Component({
  templateUrl: './configuracoes-turma.page.html',
  styleUrls: ['./configuracoes-turma.page.scss']
})
export class ConfiguracoesTurmaPage implements OnInit {

  tipo: TipoMenu = TipoMenu.DETALHE_TURMA;

  constructor(private router: Router, 
    private route: ActivatedRoute, 
    private turmaService: TurmasService) { }

  ngOnInit() {
  }

  routerTo(evtResp) {
    switch (evtResp.evt) {
      case "detalharAula" :
        this.router.navigate([`turma/${this.turmaService.turmaAberta.id}/aula/${evtResp.id}`]);
      break;
    }
  }

}
