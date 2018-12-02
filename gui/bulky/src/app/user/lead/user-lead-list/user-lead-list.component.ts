import { Component, OnInit } from '@angular/core';
import {LeadService} from "../../../_services/lead.service";

@Component({
  selector: 'app-user-lead-list',
  templateUrl: './user-lead-list.component.html',
  styleUrls: ['./user-lead-list.component.css']
})
export class UserLeadListComponent implements OnInit {

  public listLead: any[] = [];
  public loading = false;
  constructor(private leadService: LeadService) { }

  ngOnInit() {
    this.loadList();
  }

  loadList() {
    this.loading = true;
    this.leadService.listLead(undefined, undefined, undefined).then( (res: any) => {
      this.loading = false;
      this.listLead = res.output;
    }).catch( e => {
      this.loading = false;
    });
  }

}
