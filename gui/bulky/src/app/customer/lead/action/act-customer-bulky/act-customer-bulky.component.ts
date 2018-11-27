import { Component, OnInit } from '@angular/core';
import {ActionService} from '../../../../_services/action.service';
import {ParameterService} from '../../../../_services/parameter.service';
import {CustomerService} from '../../../../_services/customer.service';
import {BulkyService} from '../../../../_services/bulky.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { MatStepper } from '@angular/material';
import {Util} from '../../../../util';

@Component({
  selector: 'app-act-customer-bulky',
  templateUrl: './act-customer-bulky.component.html',
  styleUrls: ['./act-customer-bulky.component.css']
})
export class ActCustomerBulkyComponent implements OnInit {

  public myCatgAct: any;
  public listAddress: any[] = [];
  public listCatgIngom: any[] = [];
  public listPlanning: any[] = [];
  public listRowBulky: any[] = [{}];

  isLinear = false;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;

  public selectAddress: any;
  public  selectPlanning: any;
  public myPerc = 0;

  constructor(private parameterService: ParameterService, private actionServ: ActionService,
              private custService: CustomerService, private bulkyService: BulkyService, private _formBuilder: FormBuilder) { }

  ngOnInit() {
    if (this.parameterService.getCatgAction()) {
      this.myCatgAct = this.parameterService.getCatgAction();
    }
    this.loadIndirizzi();
    this.loadCatgIngombranti();
    this.loadPlanning4addr();

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
    });
  }

  loadPlanning4addr() {
    this.bulkyService.getPlanning4addr(2).then( (res: any) => {
      this.listPlanning = res.output;
    });
  }

  salva() {
    this.actionServ.saveAction(undefined, undefined, undefined, this.myCatgAct.caid).then( (res: any) => {

    });
  }

  addRow() {
    if (this.myPerc >= 100) {

    } else {
      this.listRowBulky.push({});
    }
  }

  removeRow(row: any) {
    console.log('REMOVE ROW');
  }

  selectCatgRifiuto(evt) {
    console.log('SELECT CATG RIFIUTO');
    if (!this.listRowBulky.some( r => !r.crif)) {
      this.addRow();
    }

    let a = this.listRowBulky;
    let p = this.selectPlanning;
    let ad = this.selectAddress;

    let newPer = 0;
    let myIngrombro = 0;
    let ingMax = 10;
    for (let r of this.listRowBulky) {
      if (!r.qty && r.crif && r.crif.crqtymin) {
        r.qty = r.crif.crqtymin;
      }

      if(r && r.crif && r.crif.crincombro && r.crif.crincombro > 0 && r.qty && r.qty > 0) {
        myIngrombro += r.qty * r.crif.crincombro;
      }
    }

    newPer = (100 * myIngrombro ) / ingMax;
    this.myPerc = newPer;
  }

  goBack(stepper: MatStepper){
    stepper.previous();
  }

  goForward(stepper: MatStepper){
    if (stepper.selectedIndex === 0) {
      if (!this.selectAddress || !this.selectPlanning) {
        console.log('ERRORE - Selezionare Indirizzo e Planning');
        Util.alertMsg(Util.alertWarning, 'Attenzione', 'E\' necessario selezionare l\'indirizzo e la data prima di procedere');
        return;
      }
    }
    if (stepper.selectedIndex === 1 && this.myPerc > 100) {
      Util.alertMsg(Util.alertError, 'Errore', 'Hai superato la quantia\' di materiali conferibili, rivedi i tuoi dati.');
      return;
    }
    stepper.next();
  }


  haveConfigurationRifiuti() {
    return this.listRowBulky.some( r => r.crif);
  }

  radioChange(evt) {
    // if (this.listRowBulky.some( r => r.crif)) {
    //   console.log('ALERT');
    //   Util.confirmAdvanced('Attezione', 'Modificando la pianificazione i dati presenti sui rifiuti da conferire saranno eliminati. Continuare?',
    //     Util.alertWarning, 'SI', 'Annulla', () => {
    //       this.listRowBulky = [{}];
    //       return true;
    //     }, () => {
    //       evt.preventDefault();
    //       return false;
    //     });
    // }
  }



}
