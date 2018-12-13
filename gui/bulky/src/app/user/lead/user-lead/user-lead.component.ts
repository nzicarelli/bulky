import { Component, OnInit } from '@angular/core';
import {LeadService} from "../../../_services/lead.service";
import {ParameterService} from "../../../_services/parameter.service";

@Component({
  selector: 'app-user-lead',
  templateUrl: './user-lead.component.html',
  styleUrls: ['./user-lead.component.css']
})
export class UserLeadComponent implements OnInit {

  step = 0;
  myLead: any;
  myCustomer: any;
  myAddress: any;
  azini4lead: any[] = [];
  azinilead: any[] = [];

  azione2exec: any;

  constructor(private leadServ: LeadService, private paramServ: ParameterService) { }

  ngOnInit() {
    if (this.paramServ.getIdLead() && this.paramServ.getIdLead() > 0) {
      this.loadLead();
      this.loadAzioniLead();
    }
  }


  setStep(index: number) {
    this.step = index;
  }

  nextStep() {
    this.step++;
  }

  prevStep() {
    this.step--;
  }

  loadLead() {
    this.leadServ.loadLead(this.paramServ.getIdLead()).then( (res: any) => {
      this.myLead = res.output.lead;
      this.myCustomer = res.output.customer;
      this.myAddress = res.output.address;
      this.loadAzioni4Lead();
    });
  }

  loadAzioni4Lead() {
    this.leadServ.listTipoAction4TipoLead(this.myLead.ltype).then( (res: any) => {
      this.azini4lead = res.output;
    });
  }

  loadAzioniLead() {
    this.leadServ.listAction4Lead(this.paramServ.getIdLead()).then( (res: any) => {
      this.azinilead = res.output;
    })
  }

  selectAzione(azione) {
    this.paramServ.setCatgAction(azione);
    this.azione2exec = azione;
  }

}
