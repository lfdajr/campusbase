import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  templateUrl: './turmas.home.html',
  styleUrls: ['./turmas.home.scss']
})
export class TurmasHomePage implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  routerTo(evtResp) {
    switch (evtResp.evt) {
      case "detalharCurso" :
        console.log("auqi " + evtResp.id ) ;
        this.router.navigate([`turmas/detalhe/${evtResp.id}`]);
      break;
    }
  }

}
