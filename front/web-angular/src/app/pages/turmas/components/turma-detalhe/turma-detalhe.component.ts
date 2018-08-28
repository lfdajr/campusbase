import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { TurmasService } from '../../../../component/services/turmas.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Curso } from '../../../../common/model/curso';
import { Matricula } from '../../../../common/model/matricula';

@Component({
  selector: 'turma-detalhe',
  templateUrl: './turma-detalhe.component.html',
  styleUrls: ['./turma-detalhe.component.scss']
})
export class TurmaDetalheComponent implements OnInit {

  matricula: Matricula;
  @Output() evtDetalharAula = new EventEmitter();

  constructor(private router: Router, 
    private route: ActivatedRoute, 
    private turmaService: TurmasService) { }


  ngOnInit() {
    let cursoId: number = parseInt((this.route.snapshot.paramMap.get('cursoId')));
    this.turmaService.detalharTurma(cursoId).subscribe(_matricula => {
      this.matricula = _matricula;
    });
  }

  detalharAula(id: number) {
    //localStorage.setItem("curso", JSON.stringify(this.matricula.curso));
    this.evtDetalharAula.emit({evt: "detalharAula", id: id});
  }

}