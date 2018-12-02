import { Component, OnInit } from '@angular/core';
import {AnagraficheService} from "../../../_services/anagrafiche.service";
import {DataUtilService} from "../../../_services/data-util.service";

@Component({
  selector: 'app-elenco-clienti',
  templateUrl: './elenco-clienti.component.html',
  styleUrls: ['./elenco-clienti.component.css']
})
export class ElencoClientiComponent implements OnInit {

  public listClienti: any[] = [];
  public loading = false;

  public comuni: any[] = [];
  public cSel: any;

  public listIndirizzi: any[] = [];

  public selectedCli: any;

  constructor(private anagrServ: AnagraficheService, private dataServ: DataUtilService) { }

  ngOnInit() {
    this.loadComuni();
    this.loadList();
  }

  loadList() {
    this.loading = true;
    this.anagrServ.listClienti('RENDE').then( (res: any) => {
      this.loading = false;
      this.listClienti = res.output;
    });
  }

  loadComuni() {
    this.dataServ.getComuni().then( (res: any) => {
      this.comuni = res.output;
    }).catch( e => {
    });
  }

  loadAddress(evt) {
    this.listIndirizzi = [];
    this.loadAddress4Cli(evt.data.cuid);
  }

  loadAddress4Cli(idCli) {
    this.anagrServ.listAddress4cli(idCli).then( (res: any) => {
      this.listIndirizzi = res.output;
    });
  }
}
