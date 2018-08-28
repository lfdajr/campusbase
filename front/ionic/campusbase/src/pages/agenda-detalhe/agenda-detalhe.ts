import { Component, ViewChild } from '@angular/core';
import { AlertController, ItemSliding, List, NavController, Refresher, Events, NavParams } from 'ionic-angular';
import { Agenda } from '../../model/agenda';
import { TurmasService } from '../../providers/turmas.service';
import { Curso } from '../../model/curso';

@Component({
  selector: 'agenda-detalhe',
  templateUrl: 'agenda-detalhe.html'
})
export class AgendaDetalhePage {

  spinnerOn = true;
  agenda: Agenda;
  curso: Curso = new Curso();

  constructor(
    public alertCtrl: AlertController,
    public navCtrl: NavController,
    public params: NavParams,
    public events: Events,
    public turmaService: TurmasService
  ) { 
     this.agenda = params.get("evento")
  }

  ionViewDidLoad() {
    this.load();
  }

  load() {
    this.turmaService.detalharTurma(this.agenda.destino).subscribe(_ret => {
      if (_ret)
        this.curso = _ret.curso;
    });
  }
}  