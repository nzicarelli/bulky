import {Component, Input, OnInit} from '@angular/core';
import {ParameterService} from "../../../../_services/parameter.service";
import {ActionService} from "../../../../_services/action.service";
import {Util} from "../../../../util";

@Component({
  selector: 'app-act-customer-standard',
  templateUrl: './act-customer-standard.component.html',
  styleUrls: ['./act-customer-standard.component.css']
})
export class ActCustomerStandardComponent implements OnInit {
  @Input() idCustomer: number;

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
    this.actionServ.saveAction(undefined, this.descr, this.oggetto, this.myCatgAct.caid, this.idCustomer).then( (res: any) => {
      if (res.success) {
        Util.alertMsg(Util.alertSuccess, 'OK', 'Salvataggio effettuato con successo');
      } else {
        Util.alertMsg(Util.alertError, 'KO', 'Si è verificato un errore, riprova!');
      }
    });
  }


}
