import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-lead',
  templateUrl: './user-lead.component.html',
  styleUrls: ['./user-lead.component.css']
})
export class UserLeadComponent implements OnInit {

  step = 0;

  constructor() { }

  ngOnInit() {
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

}
