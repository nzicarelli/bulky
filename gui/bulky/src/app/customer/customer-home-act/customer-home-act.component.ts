import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ActionService} from "../../_services/action.service";
import {ParameterService} from "../../_services/parameter.service";
import {LeadService} from "../../_services/lead.service";

@Component({
  selector: 'app-customer-home-act',
  templateUrl: './customer-home-act.component.html',
  styleUrls: ['./customer-home-act.component.css']
})
export class CustomerHomeActComponent implements OnInit {

  public listCatgAct: any[] = [];
  public listLead: any[] = [];
  public loading = false;

  constructor(private router: Router, private route: ActivatedRoute, private leadService: LeadService,
              private actService: ActionService, private parameterServ: ParameterService) { }

  ngOnInit() {

    this.loadAction();
    this.loadList();
  }

  loadAction() {
    this.actService.getCatgAction4customer()
      .then((res: any) => {
        console.log('ACTION LOADED ' + res);
        if (res.success) {
          this.listCatgAct = res.output;
        }
      });
  }

  gotoAction(act) {
    console.log(act);
    // this.router.navigate([{ outlets: { customerOut: null }}]);
    this.parameterServ.setCatgAction(act);
    this.router.navigate([{ outlets: { customerOut: [  act.cadoctype ] }}],
      { skipLocationChange: true, relativeTo: this.route.parent });

  }

  loadList() {
    this.loading = true;
    this.leadService.listLead(undefined, undefined, undefined).then( (res: any) => {
      this.listLead = res.output;
      this.loading = false;
    });
  }

}
