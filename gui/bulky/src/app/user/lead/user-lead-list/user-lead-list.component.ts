import { Component, OnInit } from '@angular/core';
import {LeadService} from "../../../_services/lead.service";

@Component({
  selector: 'app-user-lead-list',
  templateUrl: './user-lead-list.component.html',
  styleUrls: ['./user-lead-list.component.css']
})
export class UserLeadListComponent implements OnInit {

  public listLead: any[] = [];
  constructor(private leadService: LeadService) { }

  ngOnInit() {
    this.loadList();
  }

  loadList() {
    this.leadService.listLead(undefined, undefined, undefined).then( (res: any) => {
      this.listLead = res.output;
    });
  }

}
