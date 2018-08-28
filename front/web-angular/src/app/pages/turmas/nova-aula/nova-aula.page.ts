import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { TurmasService } from '../../../component/services/turmas.service';
import { TipoMenu } from '../../../component/page-components/context-menu/context-menu.component';
import { FormGroup } from '@angular/forms';
import { Validators } from '@angular/forms';
import { Aula } from '../../../common/model/aula';
import { ActivatedRoute } from '@angular/router';
import { Curso } from '../../../common/model/curso';

@Component({
  selector: 'nova-aula',
  templateUrl: './nova-aula.page.html',
  styleUrls: ['./nova-aula.page.scss']
})
export class NovaAulaPage implements OnInit {

  tipo: TipoMenu = TipoMenu.NOVA_AULA;
  formAula: FormGroup;

  constructor(private formBuilder: FormBuilder, 
    private router: Router,
    private route: ActivatedRoute, 
    private turmaService: TurmasService) { }

    ngOnInit() {
      this.formAula = this.formBuilder.group({
        titulo: ["", Validators.required]
      });
    }
  
    onSubmit() {
      let aula : Aula = new Aula();
      let cursoId: number = parseInt((this.route.snapshot.paramMap.get('cursoId')));          
      if (this.formAula.valid) {
        aula.titulo = this.formAula.controls.titulo.value;
        aula.curso = new Curso();
        aula.curso.id = cursoId;
  
        this.turmaService.novaAula(aula).subscribe(_aula => {
          console.log("aula gravada");
          
          if (_aula)
            this.router.navigate(['/turmas']);
        });
      }
    }
}
