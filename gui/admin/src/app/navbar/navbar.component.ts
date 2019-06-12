import {Component, OnInit} from '@angular/core';
import {NgbDropdownConfig} from '@ng-bootstrap/ng-bootstrap';
import {AuthenticationServiceService} from '../services/authentication-service.service';
import {APPCONFIG} from '../config';
import {User} from '../user/user';
import {Route, Router} from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
  providers: [NgbDropdownConfig]
})
export class NavbarComponent implements OnInit {
  AppConfig: any;
  user: User;
  public sidebarOpened = false;

  toggleOffcanvas() {
    this.sidebarOpened = !this.sidebarOpened;
    if (this.sidebarOpened) {
      document.querySelector('.sidebar-offcanvas').classList.add('active');
    }
    else {
      document.querySelector('.sidebar-offcanvas').classList.remove('active');
    }
  }

  constructor(config: NgbDropdownConfig, private auth: AuthenticationServiceService, private router: Router) {
    config.placement = 'bottom-right';
  }

  ngOnInit() {
    console.log('NavbarComponent init');
    this.AppConfig = APPCONFIG;
    this.user = this.auth.currentUserValue;
  }

  logout() {
    this.auth.logout();
    this.router.navigate(['/login']);
  }

}
