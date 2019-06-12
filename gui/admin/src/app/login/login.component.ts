import { Component, OnInit } from '@angular/core';
import {AuthenticationServiceService} from '../services/authentication-service.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  user: any = {};
  private returnUrl: any;
  constructor(private authenticationService: AuthenticationServiceService, private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.user ={
      username:'',
      password:''
    };
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  login(){
    console.log(this.user.username+" "+this.user.password);
    this.authenticationService.login(this.user.username,this.user.password).subscribe(
      data => {
        console.log(data);
        this.router.navigate([this.returnUrl]);
      },
      error => {
        console.log(error);
      });
  }

}
