import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TurmasHomePage } from './turmas.home';
import { TurmasListagemPage } from './turmas-listagem/turmas-listagem.page';
import { TurmaDetalhePage } from './turma-detalhe/turma-detalhe.page';
import { AulaDetalhePage } from './aula-detalhe/aula-detalhe.page';
import { NovaTurmaPage } from './nova-turma/nova-turma.page';
import { NovaAulaPage } from './nova-aula/nova-aula.page';
import { ConfiguracoesTurmaPage } from './configuracoes-turma/configuracoes-turma.page';

const turmasHomeRoutes: Routes = [
  {
    path: '', component: TurmasHomePage,
    children: [
      { path: '', component: TurmasListagemPage },
      { path: 'turma/nova', component: NovaTurmaPage },
      { path: 'turma/:cursoId/aula/nova', component: NovaAulaPage },
      { path: 'turma/:cursoId', component: TurmaDetalhePage },
      { path: 'turma/:cursoId/aula/:aulaId', component: AulaDetalhePage },
      { path: 'turma/:cursoId/configuracoes', component: ConfiguracoesTurmaPage }
      
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(turmasHomeRoutes)
  ],
  exports: [
    RouterModule
  ],
  providers: []
})
export class TurmasRoutingModule { }
