import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FileUploader } from 'ng2-file-upload/ng2-file-upload';

@Component({
  selector: 'context-menu',
  templateUrl: './context-menu.component.html',
  styleUrls: ['./context-menu.component.scss']
})
export class ContextMenuComponent implements OnInit {
  @Input() tipo: TipoMenu;
  @Input() upload: FileUploader;
  @Output() onFileAdded: EventEmitter<any> = new EventEmitter();
  tipoMenuType = TipoMenu;
  cursoId: number;
  

  constructor(private route: ActivatedRoute) { 
    this.cursoId = parseInt(this.route.snapshot.paramMap.get('cursoId'));
  }

  ngOnInit() {
    
  }

  fileAdded(evt) {
    this.onFileAdded.emit(evt);
  }

}

export enum TipoMenu {
  LISTAGEM_TURMAS, DETALHE_TURMA, AULA, NOVA_TURMA, NOVA_AULA
}
