import { Component, OnInit } from '@angular/core';
import {AnagraficheService} from "../../../_services/anagrafiche.service";
import {DataUtilService} from "../../../_services/data-util.service";
import {ActivatedRoute, Router} from "@angular/router";
import {LeadService} from "../../../_services/lead.service";

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
  public displayNewLead = false;
  public typeLead: number;
  public tipiLead: any[] = [];

  constructor(private anagrServ: AnagraficheService, private dataServ: DataUtilService,
              private router: Router, private route: ActivatedRoute, private leadServ: LeadService) { }

  ngOnInit() {
    this.loadComuni();
    this.loadList();
    this.loadTipoContatti();
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

  onRowSelect(evt) {
    console.log('SELECT CLI: ' + evt.data);
    this.displayNewLead = true;
  }

  loadTipoContatti() {
    this.leadServ.listTipoLead().then( (res: any) => {
      this.tipiLead = res.output;
    })
  }

  doNewContact() {
    console.log('CREO LEAD - TYPE: ' + this.typeLead);
    this.leadServ.storeLead(this.typeLead, undefined, this.selectedCli.cuid, undefined).then( (res: any) => {
      this.router.navigate([{ outlets: { userOut: ['lead'] } }],
        { skipLocationChange: true, relativeTo:  this.route.parent });
    })


  }
}
