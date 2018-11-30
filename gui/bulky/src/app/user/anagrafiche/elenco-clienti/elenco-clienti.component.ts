import { Component, OnInit } from '@angular/core';
import {AnagraficheService} from "../../../_services/anagrafiche.service";

@Component({
  selector: 'app-elenco-clienti',
  templateUrl: './elenco-clienti.component.html',
  styleUrls: ['./elenco-clienti.component.css']
})
export class ElencoClientiComponent implements OnInit {

  public listClienti: any[] = [];

  constructor(private anagrServ: AnagraficheService) { }

  ngOnInit() {
    this.loadList();
  }

  loadList() {
    this.anagrServ.listClienti('RENDE').then( (res: any) => {
      this.listClienti = res.output;
    })
  }

}
