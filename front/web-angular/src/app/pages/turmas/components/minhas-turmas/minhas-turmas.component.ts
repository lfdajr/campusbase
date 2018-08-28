import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { AuthService } from '../../../../common/service/auth.service';
import { Usuario } from '../../../../common/model/usuario';
import { TurmasService } from '../../../../component/services/turmas.service';
import { Curso } from '../../../../common/model/curso';

@Component({
  selector: 'minhas-turmas',
  templateUrl: './minhas-turmas.component.html',
  styleUrls: ['./minhas-turmas.component.scss']
})
export class MinhasTurmasComponent implements OnInit {

  usuario: Usuario;
  minhasTurmas: Curso[] = [];
  @Output() evtDetalharCurso = new EventEmitter();

  constructor(private auth: AuthService, private turmaService: TurmasService) { }

  ngOnInit() {
    this.usuario = this.auth.getUsuario();
    this.turmaService.minhasTurmas().subscribe(_cursos => {
      this.minhasTurmas = _cursos;
    });
  }

  detalharCurso(id: number) {
    this.evtDetalharCurso.emit({evt: "detalharCurso", id: id});
  }

}
