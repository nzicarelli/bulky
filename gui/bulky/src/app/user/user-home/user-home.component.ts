import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MenuItem} from "primeng/api";
import {AuthenticationService} from "../../_services/authentication.service";

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent implements OnInit {

  public myName = '';
  userMenu: MenuItem[];
  itemsMenu: MenuItem[];

  constructor(private router: Router, private route: ActivatedRoute, private authService: AuthenticationService) { }

  ngOnInit() {
    this.myName = this.authService.getName();
  }

  test() {
    // this.router.navigate([ { outlet: { user: [ 'list-lead' ]}}]);
    //this.router.navigate([{ outlets: { userOut: 'list-user'} }]);
    this.router.navigate([{ outlets: { userOut: ['list-user'] } }],
      { skipLocationChange: true, relativeTo: this.route });
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login'], {});
  }
}
