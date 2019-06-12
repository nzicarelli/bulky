import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthenticationServiceService} from './services/authentication-service.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy{

  title = 'star-admin-angular';
  authentication = true;
  private currUserSub: Subscription;
  constructor(private auth: AuthenticationServiceService){

  }

  ngOnInit(): void {
    console.log('Main INIT');
    const user = this.auth.currentUserValue;
    if (!user || this.auth.isTokenExpired()){
      this.authentication = false;
    }

    this.currUserSub = this.auth.currentUser.subscribe((user)=>{
      if (!user){
        this.authentication = false;
      }else{
        this.authentication = true;
      }
    });

  }

  ngOnDestroy(): void {
    if (this.currUserSub){
      this.currUserSub.unsubscribe();
    }
  }

}
