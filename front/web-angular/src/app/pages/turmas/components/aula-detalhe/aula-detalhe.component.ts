import { Component, OnInit, Input } from '@angular/core';
import { Aula } from '../../../../common/model/aula';
import { Curso } from '../../../../common/model/curso';
import { TurmasService } from '../../../../component/services/turmas.service';

@Component({
  selector: 'aula-detalhe',
  templateUrl: './aula-detalhe.component.html',
  styleUrls: ['./aula-detalhe.component.scss']
})
export class AulaDetalheComponent implements OnInit {

  _curso: Curso;
  _aula: Aula;

  @Input()
  set aula(aula: Aula) {
    this._aula = aula;
  }

  get aula(): Aula {
    return this._aula;
  }

  @Input()
  set curso(curso: Curso) {
    this._curso = curso;
  }

  get curso(): Curso {
    return this._curso;
  }


  constructor(private turmaService: TurmasService) { }

  ngOnInit() {

  }

  baixarArquivo(filename: string) {
    this.turmaService.baixarArquivo(filename).subscribe(res => {
      let blob = new Blob([res], { type: 'application/octet-stream' });
      let url = window.URL.createObjectURL(blob);
      window.open(url);
    });
  }

}

/*showConfigResponse() {
  this.configService.getConfigResponse()
    // resp is of type `HttpResponse<Config>`
    .subscribe(resp => {
      // display its headers
      const keys = resp.headers.keys();
      this.headers = keys.map(key =>
        `${key}: ${resp.headers.get(key)}`);

      // access the body directly, which is typed as `Config`.
      this.config = { ... resp.body };
    });
}*/