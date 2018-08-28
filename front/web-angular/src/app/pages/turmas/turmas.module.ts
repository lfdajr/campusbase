import { NgModule }       from '@angular/core';
import { FormsModule }    from '@angular/forms';
import { CommonModule }   from '@angular/common';
import { TurmasRoutingModule } from './turmas.home.routing.module';
import { TurmasListagemPage } from './turmas-listagem/turmas-listagem.page';
import { MinhasTurmasComponent } from './components/minhas-turmas/minhas-turmas.component';
import { AtividadeRecenteComponent } from '../../component/page-components/atividade-recente/atividade-recente.component';
import { TurmaDetalhePage } from './turma-detalhe/turma-detalhe.page';
import { TurmaDetalheComponent } from './components/turma-detalhe/turma-detalhe.component';
import { TurmasHomePage } from './turmas.home';
import { HeaderComponent } from '../../component/page-components/header/header.component';
import { FooterComponent } from '../../component/page-components/footer/footer.component';
import { AlertsComponent } from '../../component/page-components/header/alerts/alerts.component';
import { UserMenuComponent } from '../../component/page-components/header/user-menu/user-menu.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AulaDetalhePage } from './aula-detalhe/aula-detalhe.page';
import { AulaDetalheComponent } from './components/aula-detalhe/aula-detalhe.component';
import { TruncatePipe } from '../../common/pipe/truncate.pipe';
import { ContextMenuComponent } from '../../component/page-components/context-menu/context-menu.component';
import { NovaTurmaPage } from './nova-turma/nova-turma.page';
import { ReactiveFormsModule } from '@angular/forms'; 
import { NovaAulaPage } from './nova-aula/nova-aula.page';
import { FileUploadModule } from 'ng2-file-upload';
import { ConfiguracoesTurmaPage } from './configuracoes-turma/configuracoes-turma.page';
import { ConfiguracoesTurmaComponent } from './components/configuracoes-turma/configuracoes-turma.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    FileUploadModule,
    TurmasRoutingModule
  ],
  declarations: [
    TurmasHomePage,
    TurmasListagemPage,
    TurmaDetalhePage,
    AulaDetalhePage,
    NovaTurmaPage,
    NovaAulaPage,
    MinhasTurmasComponent,
    AtividadeRecenteComponent,
    ContextMenuComponent,
    TurmaDetalheComponent,
    HeaderComponent,
    FooterComponent,
    AlertsComponent,
    UserMenuComponent,
    AulaDetalheComponent,
    ConfiguracoesTurmaPage,
    ConfiguracoesTurmaComponent,
    TruncatePipe
  ],
  providers: [
  ]
})
export class TurmasModule {}