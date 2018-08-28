import { Component, ViewChild } from '@angular/core';

import { AlertController, ItemSliding, List, NavController, Refresher, Events } from 'ionic-angular';

import { AgendaService } from '../../providers/agenda.service';
import { Curso } from '../../model/curso';
import { Observable } from 'rxjs/Observable';
import { TurmasMensagensPage } from '../turmas-mensagens/turmas-mensagens';
import { Agenda } from '../../model/agenda';
import { BehaviorSubject } from 'rxjs';
import { environment } from '../../environments/environment';
import { TurmaSnapshot } from '../../app/util/turmaSnapshot';
import { AgendaDetalhePage } from '../agenda-detalhe/agenda-detalhe';



@Component({
  selector: 'agenda',
  templateUrl: 'agenda.html'
})
export class AgendaPage {

  spinnerOn = true;
  agenda: Agenda[] = [];

  constructor(
    public alertCtrl: AlertController,
    public navCtrl: NavController,
    public events: Events,
    public agendaService: AgendaService
  ) { 

  }

  ionViewDidLoad() {
    this.load();
    this.listenToEvents();
  }

  load() {
    this.agendaService.minhaAgenda().subscribe(_ret => {
      this.spinnerOn = false;
      this.agenda = _ret;
    });
  }

  listenToEvents() {
    this.events.subscribe('turma:novaAgenda', () => {
      this.load();
    });
  }  

  doRefresh(refresher: Refresher) {
    this.load();
    refresher.complete();
  }

  gotoAgendaDetalhe(evento: Agenda) {
    this.navCtrl.push(AgendaDetalhePage, {evento: evento});
  }

  
}  
