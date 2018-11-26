import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ActionService} from "../../_services/action.service";
import {ParameterService} from "../../_services/parameter.service";

@Component({
  selector: 'app-customer-home-act',
  templateUrl: './customer-home-act.component.html',
  styleUrls: ['./customer-home-act.component.css']
})
export class CustomerHomeActComponent implements OnInit {

  public listCatgAct: any[] = [];

  constructor(private router: Router, private route: ActivatedRoute,
              private actService: ActionService, private parameterServ: ParameterService) { }

  ngOnInit() {

    this.loadAction();
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

}
