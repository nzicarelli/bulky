import { Component, OnInit } from '@angular/core';
import {LeadService} from "../../../_services/lead.service";
import {DataUtilService} from "../../../_services/data-util.service";

@Component({
  selector: 'app-user-lead-list',
  templateUrl: './user-lead-list.component.html',
  styleUrls: ['./user-lead-list.component.css']
})
export class UserLeadListComponent implements OnInit {

  public listLead: any[] = [];
  public loading = false;
  public periodi: any[] = [];
  public pSel: any;
  public comuni: any[] = [];
  public cSel: any;
  constructor(private leadService: LeadService, private dataServ: DataUtilService) { }

  ngOnInit() {
    this.loadPeriodi();
    this.loadComuni();
  }

  loadPeriodi() {
    this.loading = true;
    this.dataServ.getPeriodi().then( (res: any) => {
      this.periodi = res.results;
      if (res.selected && res.selected.mese) {
        this.pSel = res.selected.mese;
      }
      this.loadList();
    }).catch( e => {
      this.loadList();
      this.loading = false;
    });
  }

  loadList() {
    this.loading = true;
    let da = undefined;
    let a = undefined;
    if (this.pSel && this.pSel > 0 ) {
      const d = this.periodi.find(x => x.ID === this.pSel);
      if (d) {
        da = d.DATA_DA;
        a = d.DATA_A;
      }
    }
    this.leadService.listLead(undefined, da, a).then( (res: any) => {
      this.loading = false;
      this.listLead = res.output;
    }).catch( e => {
      this.loading = false;
    });
  }

  loadComuni() {
    this.dataServ.getComuni().then( (res: any) => {
      this.comuni = res.output;
    }).catch( e => {
    });
  }

}
