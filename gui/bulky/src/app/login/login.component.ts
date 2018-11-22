import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../_services/authentication.service";
import {Util} from "../util";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public password = '';
  public user = '';
  loading = false;
  returnUrl: string;
  public textError = 'Credenziali non valide';
  public showError = false;

  public passwordWrong = false;
  public userWrong = false;

  constructor(private route: ActivatedRoute, public router: Router, private authService: AuthenticationService) { }

  ngOnInit() {
    // reset login status
    // this.authService.logout();

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';

    if (this.authService.isUser()) {
      this.goToUser();
    } else {
      this.goToCustomer();
    }
  }

  goToCustomer() {
    this.router.navigate(['/customer']);
  }

  goToUser() {
    this.router.navigate(['/user']);
  }

  login() {
    console.log('LOGIN: ' + this.user + ' - ' + this.password);
    this.showError = false;
    this.userWrong = false;
    this.passwordWrong = false;
    if (!this.user || !Util.validateEmail(this.user)) {
      this.userWrong = true;
    }

    if (!this.password || this.password.length < 3) {
      this.passwordWrong = true;
    }

    if (this.userWrong || this.passwordWrong) {
      return;
    }
    this.loading = true;
    this.authService.login(this.user, this.password)
      .subscribe(
        data => {
          this.loading = false;
          console.log('After login ' + data);
          if (data.success || (data.tk && data.tk.length > 0)) {
            console.log('ROUTE TO ' + this.returnUrl);
            // this.permessi.loadPermessi();
            // this.router.navigate([this.returnUrl]);
            if (this.authService.isUser()) {
              this.goToUser();
            } else {
              this.goToCustomer();
            }
          } else {
            console.log('NO LOGIN');
            this.showError = true;
            this.textError = data.msg;
          }
        },
        error => {
          this.loading = false;
          this.showError = true;
          this.textError = error.error.MSG;
        });
  }

  noAlert() {
    this.showError = false;
    this.userWrong = false;
    this.passwordWrong = false;
  }
}
