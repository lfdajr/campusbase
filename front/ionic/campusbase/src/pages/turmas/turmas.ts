import { Component, ViewChild } from '@angular/core';

import { AlertController, App, FabContainer, ItemSliding, List, ModalController, NavController, ToastController, LoadingController, Refresher, Events } from 'ionic-angular';

import { TurmasService } from '../../providers/turmas.service';
import { Curso } from '../../model/curso';
import { Observable } from 'rxjs/Observable';
import { TurmasMensagensPage } from '../turmas-mensagens/turmas-mensagens';
import { Matricula } from '../../model/matricula';
import { BehaviorSubject } from 'rxjs';
import { environment } from '../../environments/environment';
import { TurmaSnapshot } from '../../app/util/turmaSnapshot';



@Component({
  selector: 'turmas',
  templateUrl: 'turmas.html'
})
export class TurmasPage {

  dayIndex = 0;
  queryText = '';
  segment = 'all';
  excludeTracks: any = [];
  shownSessions: any = [];
  groups: any = [];
  confDate: string;
  matriculas: Matricula[] = [];
  turmaInfoLS: TurmaSnapshot;
  spinnerOn = true;

  constructor(
    public alertCtrl: AlertController,
    public app: App,
    public loadingCtrl: LoadingController,
    public modalCtrl: ModalController,
    public navCtrl: NavController,
    public toastCtrl: ToastController,
    public events: Events,
    public turmaService: TurmasService
  ) { 
    this.turmaInfoLS = new TurmaSnapshot();
  }

  ionViewDidLoad() {
    this.app.setTitle('Turmas');
    this.load();
    this.listenToEvents();
  }

  load() {
    this.turmaService.minhasTurmas().subscribe(_ret => {
      this.spinnerOn = false;
      this.matriculas = _ret;
      this.turmaInfoLS.compare(this.matriculas);
    });
  }

  listenToEvents() {
    this.events.subscribe('turma:novaMensagem', () => {
      this.novaMensagem();
      this.load();
    });
  }  

  goToSessionDetail(matricula: Matricula) {
    matricula.showNovidade = false;
    this.turmaInfoLS.update(matricula);
    this.navCtrl.push(TurmasMensagensPage, { cursoId: matricula.curso.id });
  }  

  novaMensagem() {
    const prompt = this.alertCtrl.create({
      title: 'Nova mensagem',
      message: "Mensagem recebida",
      buttons: [
        {
          text: 'Seguir',
          handler: data => {
            console.log('Cancel clicked');
          }
        }
      ]
    });
    prompt.present();
  }  

  /*presentFilter() {
    let modal = this.modalCtrl.create(ScheduleFilterPage, this.excludeTracks);
    modal.present();

    modal.onWillDismiss((data: any[]) => {
      if (data) {
        this.excludeTracks = data;
        this.updateSchedule();
      }
    });

  }*/

  openSocial(network: string, fab: FabContainer) {
    let loading = this.loadingCtrl.create({
      content: `Posting to ${network}`,
      duration: (Math.random() * 1000) + 500
    });
    loading.onWillDismiss(() => {
      fab.close();
    });
    loading.present();
  }

  doRefresh(refresher: Refresher) {
    this.load();
    refresher.complete();
  }


  matricularTurma() {
    const prompt = this.alertCtrl.create({
      title: 'Matricular',
      message: "Entre com o código da turma",
      inputs: [
        {
          name: 'codigo',
          placeholder: 'Código da turma'
        },
      ],
      buttons: [
        {
          text: 'Cancelar',
          handler: data => {
            console.log('Cancel clicked');
          }
        },
        {
          text: 'Seguir',
          handler: data => {
            this.turmaService.matricular(data.codigo).subscribe(_ret => {
              this.matriculas.push(_ret);
            });
          }
        }
      ]
    });
    prompt.present();
  }

  aguardandoMatricula() {
    const prompt = this.alertCtrl.create({
      title: 'Matrícula Pendente',
      message: "Aguardando professor liberar matrícula",
      buttons: [
        {
          text: 'Ok',
          handler: data => {
            console.log('Cancel clicked');
          }
        }
      ]
    });
    prompt.present();
  }
}  
