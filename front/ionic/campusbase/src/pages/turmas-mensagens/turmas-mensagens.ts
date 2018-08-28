import { Component, ViewChild } from '@angular/core';
import { NavParams, Content } from 'ionic-angular';
import { TurmasService } from '../../providers/turmas.service';
import { Mensagem } from '../../model/mensagem';
import { AuthService } from '../../providers/auth.service';
import { Usuario } from '../../model/usuario';
import { environment } from '../../environments/environment';


@Component({
  selector: 'turmas-mensagens',
  templateUrl: 'turmas-mensagens.html'
})
export class TurmasMensagensPage {
  @ViewChild(Content) content: Content;
  mensagens: Mensagem[] = [];
  cursoId: number;
  mensagemStr: string;
  urlDownload = environment.DOWNLOAD_URL;
  spinnerOn = true;

  constructor(
    public navParams: NavParams,
    public turmaService: TurmasService,
    public auth: AuthService
  ) {
    this.cursoId = this.navParams.get('cursoId');
  }

  ionViewWillEnter() {
    this.turmaService.mensagensTurma(this.cursoId).subscribe(_ret => {
      this.spinnerOn = false;
      this.mensagens = _ret;
      this.mensagens.forEach(msg => {
        msg.remetente.avatar = msg.remetente.nome.charAt(0).toUpperCase() + ".png";
      });
    });
  }

  ionViewDidEnter() {
    this.content.scrollToBottom();
  }

  enviarMensagem() {
    if (this.mensagemStr === "")
    return;
    
    let mensagem : Mensagem = new Mensagem();
    mensagem.mensagem = this.mensagemStr;
    mensagem.destino = this.cursoId; 
    mensagem.remetente = new Usuario();
    mensagem.remetente.nome = this.auth.getUsuario().nome;
    mensagem.remetente.avatar = mensagem.remetente.nome.charAt(0).toUpperCase() + ".png";

    this.mensagemStr = '';
    this.mensagens.push(mensagem);

    this.turmaService.enviarMensagemTurma(mensagem).subscribe(_ret => {
      setTimeout(() => {
        this.content.scrollToBottom();
      }, 200);
    });
    

      
  }
}
