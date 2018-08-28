import { Component, OnInit } from '@angular/core';
import { TipoMenu } from '../../../component/page-components/context-menu/context-menu.component';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Curso } from '../../../common/model/curso';
import { TurmasService } from '../../../component/services/turmas.service';
import { Router } from '@angular/router';

@Component({
  selector: 'nova-turma',
  templateUrl: './nova-turma.page.html',
  styleUrls: ['./nova-turma.page.scss']
})
export class NovaTurmaPage implements OnInit {

  tipo: TipoMenu = TipoMenu.NOVA_TURMA;
  formTurma: FormGroup;

  constructor(private formBuilder: FormBuilder, 
    private router: Router,
    private turmaService: TurmasService) { }

  ngOnInit() {
    this.formTurma = this.formBuilder.group({
      codigo: ["", Validators.required],
      nomeDisciplina: ["", Validators.required],
      titulo: ["", Validators.required],
      descricao: ["", Validators.required]
    });
  }

  onSubmit() {
    let curso : Curso = new Curso();
    if (this.formTurma.valid) {
      curso.codigo = this.formTurma.controls.codigo.value;
      curso.titulo = this.formTurma.controls.titulo.value;
      curso.descricao = this.formTurma.controls.descricao.value;
      curso.nomeDisciplina = this.formTurma.controls.nomeDisciplina.value;
      
      console.log(curso);

      this.turmaService.novaTurma(curso).subscribe(_curso => {
        if (_curso)
          this.router.navigate(['turmas']);
      });
    }
  }

}
