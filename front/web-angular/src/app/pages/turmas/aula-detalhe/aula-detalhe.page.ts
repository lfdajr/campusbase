import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { TurmasService } from '../../../component/services/turmas.service';
import { Aula } from '../../../common/model/aula';
import { Curso } from '../../../common/model/curso';
import { TipoMenu } from '../../../component/page-components/context-menu/context-menu.component';
import { FileUploader, FileItem, ParsedResponseHeaders } from 'ng2-file-upload';
import { Midia } from '../../../common/model/midia';
import { forEach } from '@angular/router/src/utils/collection';
import { environment } from '../../../../environments/environment';
//import { FileUploader } from 'ng2-file-upload';

@Component({
  templateUrl: './aula-detalhe.page.html',
  styleUrls: ['./aula-detalhe.page.scss']
})
export class AulaDetalhePage implements OnInit {
  aula: Aula = new Aula();
  curso: Curso = new Curso();
  tipo: TipoMenu = TipoMenu.AULA;
  uploader: FileUploaderExtended;
  private mainEndpoint = environment.mainEndpoint;


  constructor(private router: Router,
    private route: ActivatedRoute,
    private turmaService: TurmasService) { }

  ngOnInit() {
    let aulaId: number = parseInt((this.route.snapshot.paramMap.get('aulaId')));
    let cursoId: number = parseInt((this.route.snapshot.paramMap.get('cursoId')));

    this.uploader = new FileUploaderExtended({
      url: `${this.mainEndpoint}/turma/${cursoId}/aula/${aulaId}/upload`,
      isHTML5: true,
      autoUpload: true,
      authToken: "Bearer " + localStorage.getItem('access_token')
    });

    this.turmaService.detalharAula(aulaId, cursoId).subscribe(_aula => {
      this.aula = _aula;
      this.curso = this.turmaService.turmaAberta;
      this.uploader.aula = this.aula;
    });
  }

  enviar() {
    console.log("Enviando upload: Page");
  }

  fileAdded(arquivosFileList) {
    for (let i = 0; i < arquivosFileList.length; i++) {
      let file = arquivosFileList[i];
      let novoArquivo = new Midia();
      novoArquivo.descricao = "Enviando... " + file.name;
      novoArquivo.caminho = file.name;
      this.aula.arquivos.push(novoArquivo);
    }

  }
}

class FileUploaderExtended extends FileUploader {

  aula: Aula;

  onCompleteItem(item: FileItem, response: string, status: number, headers: ParsedResponseHeaders): any {
    super.onCompleteItem(item, response, status, headers);
    ///console.log(this.aula);
    //console.log(this.aula.arquivos);
    this.aula.arquivos = this.aula.arquivos.filter(_arq => _arq.caminho !== item._file.name);
    
    let midia = new Midia();
    Object.assign(midia, JSON.parse(response));
    this.aula.arquivos.push(midia);
  }
    
}
