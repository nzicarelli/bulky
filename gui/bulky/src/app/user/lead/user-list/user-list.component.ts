import { Component, OnInit } from '@angular/core';
import {AnagraficheService} from "../../../_services/anagrafiche.service";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  public listUser: any[];
  public selectedLead: any;
  public myUser: any;
  public displayUser = false;

  constructor(private aServ: AnagraficheService) { }

  ngOnInit() {
    this.aServ.listUser().then( (res: any) => {
      this.listUser = res.output;
    });
  }

  onRowSelect(evt) {
    console.log('CLICK ROW ' + evt);
    this.displayUser = true;
  }

  closeUser() {
    this.myUser = undefined;
    this.displayUser = false;
  }
}
