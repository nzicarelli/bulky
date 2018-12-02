import { Component, OnInit } from '@angular/core';
import {AnagraficheService} from "../../../_services/anagrafiche.service";

@Component({
  selector: 'app-elenco-clienti',
  templateUrl: './elenco-clienti.component.html',
  styleUrls: ['./elenco-clienti.component.css']
})
export class ElencoClientiComponent implements OnInit {

  public listClienti: any[] = [];
  public loading = false;

  constructor(private anagrServ: AnagraficheService) { }

  ngOnInit() {
    this.loadList();
  }

  loadList() {
    this.loading = true;
    this.anagrServ.listClienti('RENDE').then( (res: any) => {
      this.loading = false;
      this.listClienti = res.output;
    })
  }

}
