import { Component, OnInit } from '@angular/core';
import {ParameterService} from "../../../../_services/parameter.service";
import {ActionService} from "../../../../_services/action.service";

@Component({
  selector: 'app-act-customer-standard',
  templateUrl: './act-customer-standard.component.html',
  styleUrls: ['./act-customer-standard.component.css']
})
export class ActCustomerStandardComponent implements OnInit {

  public myCatgAct: any;
  public descr: string = '';
  public oggetto: string = '';

  constructor(private parameterService: ParameterService, private actionServ: ActionService) { }

  ngOnInit() {
    if (this.parameterService.getCatgAction()) {
      this.myCatgAct = this.parameterService.getCatgAction();
    }
  }

  salva() {
    this.actionServ.saveAction(undefined, this.descr, this.oggetto, this.myCatgAct.caid).then( (res: any) => {

    });
  }

}
