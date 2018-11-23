import { Component, OnInit } from '@angular/core';
import {ActionService} from "../../../../_services/action.service";
import {ParameterService} from "../../../../_services/parameter.service";
import {CustomerService} from "../../../../_services/customer.service";
import {BulkyService} from "../../../../_services/bulky.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-act-customer-bulky',
  templateUrl: './act-customer-bulky.component.html',
  styleUrls: ['./act-customer-bulky.component.css']
})
export class ActCustomerBulkyComponent implements OnInit {

  public myCatgAct: any;
  public listAddress: any[] = [];
  public listCatgIngom: any[] = [];

  isLinear = false;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;

  constructor(private parameterService: ParameterService, private actionServ: ActionService,
              private custService: CustomerService, private bulkyService: BulkyService, private _formBuilder: FormBuilder) { }

  ngOnInit() {
    if (this.parameterService.getCatgAction()) {
      this.myCatgAct = this.parameterService.getCatgAction();
    }
    this.loadIndirizzi();
    this.loadCatgIngombranti();

    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
  }

  loadIndirizzi() {
    this.custService.getMyAddress().then( (res: any) => {
      this.listAddress = res.output;
    })
  }

  loadCatgIngombranti() {
    this.bulkyService.getCatgIngombranti().then( (res: any) => {
      this.listCatgIngom = res.output;
    })
  }

  salva() {
    this.actionServ.saveAction(undefined, undefined, undefined, this.myCatgAct.caid).then( (res: any) => {

    });
  }

}
