import { Component, OnInit } from '@angular/core';
import {AnagraficheService} from "../../../_services/anagrafiche.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  public listUser: any[];

  constructor(private aServ: AnagraficheService) { }

  ngOnInit() {
    this.aServ.listUser().then( (res: any) => {
      this.listUser = res.result;
    });
  }

}
