import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../../_services/authentication.service";
import {ActionService} from "../../_services/action.service";

@Component({
  selector: 'app-customer-home',
  templateUrl: './customer-home.component.html',
  styleUrls: ['./customer-home.component.css']
})
export class CustomerHomeComponent implements OnInit {

  public myName = '';

  constructor(private router: Router, private route: ActivatedRoute,
              private authService: AuthenticationService, private actService: ActionService) { }

  ngOnInit() {
    this.myName = this.authService.getName();
    this.loadAction();
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login'], {});
  }

  loadAction() {
    this.actService.getCatgAction4customer()
      .then((res: any) => {
        console.log('ACTION LOADED ' + res);
      });
  }
}
