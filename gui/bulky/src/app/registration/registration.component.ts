import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../_services/authentication.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Util} from "../util";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  public cognome = '';
  public nome = '';
  public cf = '';
  public user = '';
  public password = '';
  public password2 = '';

  loading = false;
  public textError = '';
  public showError = false;
  public passwordWrong = false;
  public userWrong = false;


  public cfWrong = false;
  public cognomeWrong = false;
  public psswWrong = false;
  public pssw2Wrong = false;
  public emailWrong = false;


  constructor(private route: ActivatedRoute, public router: Router, private authService: AuthenticationService) { }

  ngOnInit() {
  }

  goToLanding() {
    this.router.navigate(['/']);
  }

  register() {
    console.log('REGISTER: ' + this.user + ' - ' + this.password);
    this.showError = false;
    this.userWrong = false;
    this.passwordWrong = false;


    let hasWrong = false;

    if (!this.cognome || this.cognome.length < 3) {
      this.cognomeWrong = true;
      hasWrong = true;
    }
    if (!this.cf || this.cf.length < 3) {
      this.cfWrong = true;
      hasWrong = true;
    }
    if (!this.user || !Util.validateEmail(this.user)) {
      //TODO controllare formato
      this.emailWrong = true;
      hasWrong = true;
    }
    if (!this.password || this.password.length < 3) {
      this.psswWrong = true;
      hasWrong = true;
    }

    if (!this.password2 || this.password2.length < 3) {
      this.pssw2Wrong = true;
      hasWrong = true;
    }

    if (this.password2 !== this.password) {
      this.pssw2Wrong = true;
      this.psswWrong = true;
      hasWrong = true;
    }

    if (hasWrong) {
      return;
    }
    this.loading = true;

    this.authService.register(this.nome, this.cognome, this.user, this.password, this.cf)
      .then((data: any) => {
          this.loading = false;
          console.log('After register ' + data);
          if (data.success || (data.tk && data.tk.length > 0)) {
            this.goToLanding();
          } else {
            console.log('NO LOGIN');
            this.showError = true;
            this.textError = data.msg;
          }
        },
        error => {
          this.loading = false;
          this.showError = true;
          this.textError = 'Errore';
        });
  }

  noAlert() {
    this.showError = false;
    this.cognomeWrong = false;
    this.cfWrong = false;
    this.userWrong = false;
    this.psswWrong = false;
    this.pssw2Wrong = false;
    this.emailWrong = false;
  }

}
