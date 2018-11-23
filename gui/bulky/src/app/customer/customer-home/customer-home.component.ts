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

    this.router.navigate([{ outlets: { customerOut: ['home-act'] } }],
      { skipLocationChange: true, relativeTo: this.route });
    this.myName = this.authService.getName();
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login'], {});
  }

  gotoAction(act) {
    console.log(act);
    // this.router.navigate(['(customerOut:act-standard)'], {});

    this.router.navigate([{ outlets: { customerOut: ['act-standard'] } }],
       { skipLocationChange: true, relativeTo: this.route });
  }

}
