import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../_services/authentication.service";

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
  public textError = '';
  public showError = false;

  public passwordWrong = false;
  public userWrong = false;

  constructor(private route: ActivatedRoute, public router: Router, private authService: AuthenticationService) { }

  ngOnInit() {
    // reset login status
    this.authService.logout();

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  goToCustomer() {
    this.router.navigate(['customer']);
  }

  goToUser() {
    this.router.navigate(['/user']);
  }

  login() {
    console.log('LOGIN: ' + this.user + ' - ' + this.password);
    this.userWrong = false;
    this.passwordWrong = false;
    if (!this.user || this.user.length < 6) {
      this.userWrong = true;
    }

    if (!this.password || this.password.length < 3) {
      this.passwordWrong = true;
    }

    if (this.userWrong || this.passwordWrong) {
      return;
    }

    this.authService.login(this.user, this.password)
      .subscribe(
        data => {
          console.log('After login ' + data);
          if (data.success) {
            console.log('ROUTE TO ' + this.returnUrl);
            // this.permessi.loadPermessi();
            this.router.navigate([this.returnUrl]);
          } else {
            console.log('NO LOGIN');
            this.showError = true;
            this.textError = data.msg;
          }
          this.loading = false;
        },
        error => {
          this.loading = false;
          this.showError = true;
          this.textError = 'Errore';
        });
  }
}
